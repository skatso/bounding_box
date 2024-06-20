public class Box {
    int top;
    int bottom;
    int right;
    int left;
    boolean hasOverlap;

    Box(int i, int j) {
        this.top = i;
        this.bottom = i;
        this.right = j;
        this.left = j;
        this.hasOverlap = false;
    }

    public int computeArea() {
        return (bottom - top + 1) * (right - left + 1);
    }

    public boolean overlaps(Box other) {
        if(this.top > other.bottom || this.bottom < other.top) {
            return false;
        }
        if(this.right < other.left || this.left > other.right) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //Update the coordinates by adding 1 to match the desired grid output
        return String.format("(%s,%s),(%s,%s)", top+1, left+1, bottom+1, right+1);
    }
}