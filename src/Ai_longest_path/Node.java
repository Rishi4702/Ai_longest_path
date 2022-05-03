package Ai_longest_path;

public class Node implements Comparable<Node>{
    int vertex;
    double weight;

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }
    double h;
    String psf;

    boolean visited =false;

    Node(int v, double w) {
        vertex = v;
        weight = w;
    }

    public void setPsf(String psf) {
        this.psf = psf;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    Node(int v, double w, String s,double h){
        vertex=v;
        weight=w;
        psf=s;
        this.visited=visited;
        this.h=h;
    }

    int getVertex() {
        return vertex;
    }

    double getWeight() {
        return weight;
    }
    String getPsf(){
        return psf;
    }

    @Override
    public int compareTo(Node obj) {
        if(this.weight+this.h< obj.weight+obj.h)
            return -1;
        else if(obj.weight+obj.h<this.weight+this.h)
            return 1;
        return 0;
    }
}