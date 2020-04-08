
package g54892.humbug.model;

import static g54892.humbug.model.SquareType.GRASS;
import static g54892.humbug.model.SquareType.STAR;

/**
 * This class represents spiders on the board.
 * @author Talhaoui Yassin (YT) <54892@etu.he2b.be>
 */
public class Spider extends Animal {

    /**
     * Constructor of positionOnBoard.
     * @param positionOnBoard the current position of the animals.
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the given spiders on the gaming board.
     * @param board the gaming board.
     * @param direction the direction of the deplacement.
     * @param animals represents one or a lot of spiders.
     * @return The deplacement of one of the spiders on the board.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position pos = super.getPositionOnBoard();
        boolean isFree = true;

        if (board.getSquare(pos).hasWall(direction)) {
            return nextWall(board, direction, animals);
        }
        for (int i = 0; i < animals.length; i++) {

            if (animals[i].getPositionOnBoard().equals(pos.next(direction))) {
                return getPositionOnBoard();
            }

            if (animals[1].getPositionOnBoard().equals(pos.next(direction))) {
                return pos;
            }
            if (isFree && Board.getInitialBoard().isInside(pos.next(direction))) {
                if (board.getSquare(pos.next(direction)).hasWall(direction.opposite())) {
                    return pos;
                }

            }
            pos = pos.next(direction);
            setPositionOnBoard(pos);

        }

        if (!board.isInside(pos.next(direction))) {
            return null;
        }

        int i = 0;
        while (i < animals.length) {

            Spider sp = new Spider(pos);
            if (board.getSquares()[animals[i].getPositionOnBoard().getRow()][animals[i].getPositionOnBoard().getColumn()].getType().equals(STAR)) {
                animals[i].setOnStar(true);
            }
            if (animals[i].isOnStar()) {
                board.getSquares()[animals[i].getPositionOnBoard().getRow()][animals[i].getPositionOnBoard().getColumn()].setType(GRASS);
            } else {
                animals[i].setOnStar(false);
            }
            i++;
        }

        return getPositionOnBoard();
    }

    /**
     * @param board the gaming board.
     * @param direction the direction of the deplacement.
     * @param animals represents one or a lot of snails.
     * @return the initial position if there is a wall
     */
    private Position nextWall(Board board, Direction direction, Animal... animals) {
        int i = 0;
        while (i < animals.length) {
            Position posi = animals[i].getPositionOnBoard();
            if (board.getSquare(posi).hasWall(direction)) {
                return posi;
            }
            i++;
        }
        return null;

    }

}
    
   

