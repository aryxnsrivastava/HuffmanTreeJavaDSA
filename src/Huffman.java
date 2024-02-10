import java.util.*;

public class Huffman {

    public BinaryTree<Pair> HuffmanTree(Queue<BinaryTree<Pair>> S) {

        //initializing Queue T
        Queue<BinaryTree<Pair>> T = new LinkedList<>();


        //loop until only huffman tree is left, which also means that huffman tree is the last index of Queue T.
        while (S.size() + T.size() > 1) {

            //A and B are the smallest tree of (S,T).
            BinaryTree<Pair> A = smallestTree(S, T);
            BinaryTree<Pair> B = smallestTree(S, T);

            //creating a new BinaryTree P to store value of smallest Tree's
            BinaryTree P = new BinaryTree<>();

            //creating root for BinaryTree P.
            Pair R = new Pair('0', (A != null ? A.getData().getProb() : 0) + (B != null ? B.getData().getProb() : 0));

            //making root and attaching A and B to the BinaryTree P.
            P.makeRoot(R);
            P.attachLeft(A != null ? A : new BinaryTree<>());
            P.attachRight(B != null ? B : new BinaryTree<>());

            //Enqueue P to T.
            if (A == null && B == null) {
                break;
            }
            T.add(P);
        }

        while (T.size() > 1) {
            BinaryTree<Pair> A = T.poll();
            BinaryTree<Pair> B = T.poll();

            Pair R = new Pair('0', (A.getData().getProb() + B.getData().getProb()));

            BinaryTree<Pair> P = new BinaryTree<>();
            P.makeRoot(R);
            P.attachLeft(A);
            P.attachRight(B);
            T.add(P);
        }

        //returning Huffman Tree
        return T.poll();
    }

    public static BinaryTree<Pair> smallestTree(Queue<BinaryTree<Pair>> S, Queue<BinaryTree<Pair>> T) {

        //if T is empty return smallest value which is also the first index of the Queue and Dequeue it.
        if (T.isEmpty()) {
            return S.poll();
        }

        //if T is not empty
        else {
            //creating one and two BinaryTree's
            BinaryTree<Pair> QueueS;
            BinaryTree<Pair> QueueT;

            //making one and two as first index of S and T respectively
            if (S.isEmpty()) {
                return null;
            } else {
                QueueS = S.peek();
            }

            if (T.isEmpty()) {
                return null;
            } else {
                QueueT = T.peek();
            }

            //QueueS and QueueT are first index's of S and T respectively which are being compared.
            //if probability of S is less than T, return first index of S and dequeue it.
            if ((QueueS != null) && (QueueS.getData().getProb() < QueueT.getData().getProb())) {
                return S.poll();
            }
            //else return first index of T and dequeue it.
            else {
                return T.poll();
            }
        }
    }

    public String EncodedLine(String text, BinaryTree<Pair> letters) {
        String[] encoding = findEncoding(letters);
        StringBuilder encodedLine = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                encodedLine.append(ch);
            } else {
                //65 is the ASCII value of "A".
                encodedLine.append(encoding[ch - 'A']);
            }
        }
        return encodedLine.toString();
    }

    public String[] findEncoding(BinaryTree<Pair> bt) {
        String[] result = new String[26];
        findEncoding(bt, result, "");
        return result;
    }

    public void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix) {
        // test is node/tree is a leaf
        if (bt.getLeft() == null && bt.getRight() == null) {
            a[bt.getData().getValue() - 65] = prefix;
        }

        // recursive calls
        else {
            findEncoding(bt.getLeft(), a, prefix + "0");
            findEncoding(bt.getRight(), a, prefix + "1");
        }
    }

    public String decode(String encodedLine, BinaryTree<Pair> letters) {

        //initializing variables
        StringBuilder decodedLine = new StringBuilder();
        BinaryTree<Pair> storeLetters = letters;
        char[] characters = encodedLine.toCharArray();

        //for loop to go through every index of the arraylist and decode it.
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == ' ') {
                decodedLine.append(characters[i]);
            } else if (characters[i] == '0') {
                storeLetters = storeLetters.getLeft();
            } else if (characters[i] == '1') {
                storeLetters = storeLetters.getRight();
            }

            if (storeLetters.getLeft() == null && storeLetters.getRight() == null) {
                decodedLine.append(storeLetters.getData().getValue());
                storeLetters = letters;
            }
        }
        return decodedLine.toString();
    }

}

