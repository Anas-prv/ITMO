import json
import numpy as np
import networkx as nx
import plotly.graph_objects as go
from scipy.spatial import distance
from shapely.geometry import LineString, Point

with open('config.json', 'r') as config:
    config = json.load(config)

with open('model.json', 'r') as model:
    model = json.load(model)

source = tuple(model['source'])
target = tuple(model['target'])
ceiling = model['ceiling']
objects = model['objects']
offset = config['offset']


def create_grid():
    if min(coord[2] for coord in ceiling) == max(coord[2] for coord in ceiling):
        z = ceiling[0][2]
    else:
        print(f"Потолок задан неверно")
        raise ValueError
    grid = []
    for x in range(min(coord[0] for coord in ceiling), max(coord[0] for coord in ceiling) + 1, config['grid_ceil']):
        for y in range(min(coord[1] for coord in ceiling), max(coord[1] for coord in ceiling) + 1, config['grid_ceil']):
            grid.append((x, y, z))
            grid.append((x, y, z - config['cable_h']))
    return grid


def create_graph():
    graph = nx.Graph()
    objects_found = [LineString(obj) for obj in objects]
    for current_point in grid_points:
        if not np.isfinite(current_point).all():
            continue
        try:
            too_close = any(line.distance(Point(current_point)) < offset for line in objects_found)
        except ValueError:
            print(f"Расстояние меньше допустимого")
            continue
        if too_close:
            continue
        for current_offset in [
            (config['grid_ceil'], 0, 0), (0, config['grid_ceil'], 0),
            (-config['grid_ceil'], 0, 0), (0, -config['grid_ceil'], 0),
            (0, 0, -config['cable_h']),
            (0, 0, config['cable_h'])
        ]:
            next_point = tuple(np.array(current_point) + np.array(current_offset))
            if not np.isfinite(next_point).all():
                continue
            if tuple(next_point) in map(tuple, grid_points):
                path = LineString([current_point, next_point])
                try:
                    correct_step = all(
                        line.distance(path) >= offset for line in objects_found
                    )
                except ValueError:
                    print(f"Произошло пересечение с объектами")
                    continue
                if correct_step:
                    try:
                        graph.add_edge(tuple(current_point), next_point,
                                       weight=distance.euclidean(current_point, next_point))
                    except ValueError:
                        print(f"Ошибка при добавлении ребра между {current_point} и {next_point}")
                        continue
                    break
    return graph


def draw_cable(path_x, path_y, path_z):
    arr_x, arr_y, arr_z = [], [], []
    for i in range(len(path_x) - 1):
        x1, y1, z1 = path_x[i], path_y[i], path_z[i]
        x2, y2, z2 = path_x[i + 1], path_y[i + 1], path_z[i + 1]
        cable_width = config['cable_w'] / 2
        arr_x.extend([x1 - cable_width, x1 + cable_width, x2 + cable_width, x2 - cable_width])
        arr_y.extend([y1 - cable_width, y1 + cable_width, y2 + cable_width, y2 - cable_width])
        arr_z.extend([z1, z1, z2, z2])
    return arr_x, arr_y, arr_z


def draw():
    fig = go.Figure()
    fig.add_trace(go.Mesh3d(
        x=c_x, y=c_y, z=c_z,
        color='gray', opacity=0.5, name='ceiling'
    ))
    fig.add_trace(go.Scatter3d(
        x=g_x, y=g_y, z=g_z,
        mode='markers',
        marker=dict(size=2, color='gray'),
        name='grid'
    ))
    fig.add_trace(go.Scatter3d(
        x=[source[0]], y=[source[1]], z=[source[2]],
        mode='markers',
        marker=dict(size=8, color='yellow'),
        name='source point'
    ))
    fig.add_trace(go.Scatter3d(
        x=[target[0]], y=[target[1]], z=[target[2]],
        mode='markers',
        marker=dict(size=8, color='purple'),
        name='target point'
    ))
    fig.add_trace(go.Scatter3d(
        x=o_x, y=o_y, z=o_z,
        mode='lines',
        line=dict(color='red', width=5),
        name='objects'
    ))
    fig.add_trace(go.Scatter3d(
        x=cable_x, y=cable_y, z=cable_z,
        mode='lines',
        line=dict(color='orange', width=5),
        name='cable'
    ))
    fig.add_trace(go.Scatter3d(
        x=p_x, y=p_y, z=p_z,
        mode='lines',
        line=dict(color='green', width=5),
        name='shortest path'
    ))
    fig.update_layout(
        scene=dict(
            xaxis_title='X',
            yaxis_title='Y',
            zaxis_title='Z',
        ),
        margin=dict(l=0, r=0, b=0, t=0)
    )
    fig.show()


if __name__ == '__main__':
    grid_points = create_grid()
    shortest_path = nx.shortest_path(create_graph(), source=source, target=target)
    c_x, c_y, c_z = zip(*ceiling)
    g_x, g_y, g_z = zip(*grid_points)
    p_x, p_y, p_z = zip(*shortest_path)
    o_x, o_y, o_z = zip(*[point for obj in objects for point in obj])
    cable_x, cable_y, cable_z = draw_cable(p_x, p_y, p_z)
    draw()
