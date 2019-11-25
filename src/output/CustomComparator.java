package src.output;

import java.util.Comparator;

class CustomComparator implements Comparator<BoardInfo> {
    @Override
    public int compare(BoardInfo o1, BoardInfo o2) {
        return o1.getScore() - o2.getScore();
    }
}