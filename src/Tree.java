
public class Tree {
    Contact data;
    Tree left;
    Tree right;

    public Tree(Contact d) {
        data = d;
        left = null;
        right = null;
    }

    public Contact getContact() {
        return data;
    }

    public void setContact(Contact data) {
        this.data = data;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
