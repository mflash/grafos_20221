import sys
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import collections as mc

if len(sys.argv) == 1:
    print("plotgraph.py [arquivo de saída com o grafo]")
    sys.exit(1)

# Leitura do arquivo de pontos

y, x = np.loadtxt('dados.csv', delimiter=';', unpack=True, dtype=float)

# Leitura do arquivo de saída

data = np.loadtxt(sys.argv[1], delimiter=' ', dtype=int)

outcolor = [0,0,0,0.1]
incolor  = [1,0,0,1.0]

edges = [ [ (x[e[0]],y[e[0]]),(x[e[1]],y[e[1]]) ] for e in data] 
colours = [outcolor if e[2]==0 else incolor for e in data]
widths = [1 if e[2]==0 else 4 for e in data]

edgecol= mc.LineCollection(edges, colors=colours, linewidths=widths)

plt.rcParams["figure.figsize"] = (6,6)
fig, ax = plt.subplots()
ax.add_collection(edgecol)
ax.autoscale()
ax.margins(0.1)
plt.axis('equal')
plt.scatter(x,y,s=1)
plt.show()
