import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BishopTest {

	@Test
	public void testWhiteBishopMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateBishopMoves(board, 3, 2, true);
		System.out.println("White Bishop Moves: " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("d3");
		expectedMoves.add("e2");
		expectedMoves.add("f1");
		expectedMoves.add("b5");
		expectedMoves.add("a6");
		expectedMoves.add("b3");
		expectedMoves.add("a2");
		expectedMoves.add("d5");
		expectedMoves.add("e6");
		expectedMoves.add("f7");
		expectedMoves.add("g8");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackBishopMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateBishopMoves(board, 5, 5, false);
		System.out.println("Black Bisho Moves: " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("e5");
		expectedMoves.add("d4");
		expectedMoves.add("g7");
		expectedMoves.add("h8");
		expectedMoves.add("g5");
		expectedMoves.add("e7");
		expectedMoves.add("d8");
		expectedMoves.add("c3");
		expectedMoves.add("h4");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}
}