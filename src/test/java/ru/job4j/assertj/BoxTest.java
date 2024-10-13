package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(-1, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberOfVerticesCube() {
        Box box = new Box(8, 12);
        int vrtx = box.getNumberOfVertices();
        assertThat(vrtx).isEqualTo(8);
    }

    @Test
    void numberOfVerticesSphere() {
        Box box = new Box(0, 10);
        int vrtx = box.getNumberOfVertices();
        assertThat(vrtx).isEqualTo(0);
    }

    @Test
    void isExistTetrahedron() {
        Box box = new Box(4, 6);
        int vrtx = box.getNumberOfVertices();
        assertThat(vrtx).isNotZero();
    }

    @Test
    void isExistUnknown() {
        Box box = new Box(-1, -2);
        int vrtx = box.getNumberOfVertices();
        assertThat(vrtx).isNegative();
    }

    @Test
    void areaCube() {
        Box box = new Box(8, 12);
        double area = box.getArea();
        assertThat(area).isEqualTo(864.0);
    }

    @Test
    void areaSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63d, withPrecision(0.01d));
    }
}