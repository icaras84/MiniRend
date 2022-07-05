package com.ryo616.miniRend.core.general.util;

import java.util.Arrays;
import java.util.Objects;

public class MRMap<T> {

    private volatile T[][] map;
    private final int width, height, idLimit;
    private int currentID;

    public MRMap(int width, int height, T[]... dataset){
        this.width = width;
        this.height = height;
        this.idLimit = dataset.length;

        this.map = (T[][]) new Object[dataset.length][width * height];
        for (int i = 0; i < dataset.length; i++) {
            this.map[i] = dataset[i];
        }
    }

    public int getCurrentMapID(){
        return this.currentID;
    }

    public MRMap setCurrentMapID(int id) throws Exception {
        if (id >= (idLimit - 1) || id < 0) {
            Exception e = new ArrayIndexOutOfBoundsException("Sample ID inputted isn't included in this set!");
            e.printStackTrace();
            throw e;
        }
        this.currentID = id;
        return this;
    }

    public T[] getCurrentMap(){
        return this.map[currentID];
    }

    public MRMap setCurrentMap(T[] map) throws Exception {
        if (this.map[currentID].length != map.length) {
            Exception e = new ArrayIndexOutOfBoundsException("Maps are not of equivalent length!");
            e.printStackTrace();
            throw e;
        }
        this.map[currentID] = map;
        return this;
    }

    public T[] getMap(int id) throws Exception {
        if (id >= (idLimit - 1) || id < 0) {
            Exception e = new ArrayIndexOutOfBoundsException("ID inputted isn't included in this set!");
            e.printStackTrace();
            throw e;
        }
        return this.map[id];
    }

    public MRMap setMap(int id, T[] map) throws Exception {
        if ((id >= (idLimit - 1) || id < 0) && (this.map[currentID].length != map.length)) {
            Exception e = new ArrayIndexOutOfBoundsException("ID inputted isn't included in this set!");
            e.printStackTrace();
            throw e;
        }
        this.map[id] = map;
        return this;
    }

    public T getData(int x, int y) throws Exception {
        if (x >= width || y >= height || x < 0 || y < 0) {
            Exception e = new ArrayIndexOutOfBoundsException("Map index is out of bounds! X: " + x + " Y: " + y);
            e.printStackTrace();
            throw e;
        }
        return this.map[currentID][x + y * width];
    }

    public MRMap setData(int x, int y, T data) throws Exception {
        if (x >= width || y >= height || x < 0 || y < 0) {
            Exception e = new ArrayIndexOutOfBoundsException("Map index is out of bounds! X: " + x + " Y: " + y);
            e.printStackTrace();
            throw e;
        }
        this.map[currentID][x + y * width] = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MRMap<?> mrMap = (MRMap<?>) o;
        return width == mrMap.width && height == mrMap.height && idLimit == mrMap.idLimit && currentID == mrMap.currentID && Arrays.equals(map, mrMap.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height, idLimit, currentID);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }
}
