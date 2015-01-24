package at.pwd.asciishop.app;

/**
 * It would be better to just say: public class AsciiStack extends LinkedList<AsciiImage> { }
 * Created by rfischer on 02.12.14.
 */
public class AsciiStack {
    private static class AsciiStackNode {
        private AsciiImage image;
        private AsciiStackNode next;

        public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
            this.image = image;
            this.next = next;
        }

        int size() {
            return 1 + ((next != null) ? next.size() : 0);
        }
    }

    private AsciiStackNode current;

    public AsciiStack() { }

    public void push(final AsciiImage image) {
        current = new AsciiStackNode(image, current);
    }

    public AsciiImage pop() {
        AsciiStackNode cur = current;
        current = current.next;
        return cur.image;
    }

    public AsciiImage peek() {
        return current.image;
    }

    public boolean empty() {
        return current == null;
    }

    public int size() {
        return current.size();
    }
}
