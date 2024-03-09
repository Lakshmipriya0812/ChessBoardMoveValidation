import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class ChessBoardTest {

	public static String printBoard(char[][] board) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("  +---+---+---+---+---+---+---+---+\n");
		for (int row = 0; row < 8; row++) {
			stringBuilder.append((8 - row)).append(" |");
			for (int col = 0; col < 8; col++) {
				stringBuilder.append(" ").append(ChessBoard.getChessPieceSymbol(board[row][col])).append(" |");
			}
			stringBuilder.append("\n");
			stringBuilder.append("  +---+---+---+---+---+---+---+---+\n");
		}
		stringBuilder.append("    a   b   c   d   e   f   g   h\n");
		return stringBuilder.toString();
	}

	@Test
	public void testOneKing_OneKing() {
		System.out.println("Checking one King ");
		assertTrue(ChessBoard.oneKing("Ke1,Qe7,Re3,Ra4,Ba3,Ng6", 'K'));
		assertTrue(ChessBoard.oneKing("Ke8,Rc4,Bc2,Bb8,Pf4", 'k'));
	}

	@Test
	public void testOneKing_MultipleKings() {
		System.out.println("Checking Multiple King Inputs ");
		assertFalse(ChessBoard.oneKing("Ke1,Qe7,Re3,Ra4,Ba3,Ng6,Kg7", 'K'));
		assertFalse(ChessBoard.oneKing("Ke8,Rc4,Bc2,Bb8,Pf4,Kh1", 'k'));
	}

	@Test
	public void testConfirmMaxPieces_LessThanMax() {
		System.out.println("Total Pieces confirmation ");
		assertTrue(ChessBoard.confirmMaxPieces("Ke1,Qe7,Re3,Ra4,Ba3,Ng6"));
		assertTrue(ChessBoard.confirmMaxPieces("Ke8,Rc4,Bc2,Bb8,Pf4"));
	}

	@Test
	public void testConfirmMaxPieces_MaximumPieces() {
		assertTrue(ChessBoard.confirmMaxPieces("Ke1,Qe7,Re3,Ra4,Ba3,Ng6,Pb7,Pa2,Rh8,Bh2,Bf7,Nf3,Nb8"));
	}

	@Test
	public void testConfirmMaxPieces_ExceedsMax() {
		System.out.println("Exceeding Total Pieces ");
		assertTrue(ChessBoard.confirmMaxPieces("Ke1,Qe7,Re3,Ra4,Ba3,Ng6,Pb7,Pa2,Rh8,Bh2,Bf7,Nf3,Nb8"));
		assertFalse(
				ChessBoard.confirmMaxPieces("Ke1,Qe7,Re3,Ra4,Ba3,Ng6,Pb7,Pa2,Rh8,Bh2,Bf7,Nf3,Nb8,Ke4,Pa2,Invalid,P"));
	}

	@Test
	public void testValidFormat_ValidFormat() {
		System.out.println("Valid Input Format ");
		assertTrue(ChessBoard.validFormat("Ke1"));
		assertTrue(ChessBoard.validFormat("Qe7"));
	}

	@Test
	public void testValidFormat_InvalidFormat() {
		System.out.println("Invalid Input Format ");
		assertFalse(ChessBoard.validFormat("K"));
		assertFalse(ChessBoard.validFormat("Qe"));
		assertFalse(ChessBoard.validFormat("Rf34"));
	}

	@Test
	public void testHasOverlap_NoOverlap() {
		System.out.println("Overlap ");
		assertFalse(ChessBoard.hasOverlap("Ke1,Qe7,Re3,Ra4,Ba3,Ng6"));
		assertFalse(ChessBoard.hasOverlap("Ke8,Rc4,Bc2,Bb8,Pf4"));
	}

	@Test
	public void testPlacePiecesOnBoard_WhitePiecesPlacement() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Qe7,Re3,Ra4,Ba3,Ng6";
		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);

		assertEquals('K', board[0][4]); // King (Ke1)
		assertEquals('Q', board[6][4]); // Queen (Qe7)
		assertEquals('R', board[2][4]); // Rook (Re3)
		assertEquals('R', board[3][0]); // Rook (Ra4)
		assertEquals('B', board[2][0]); // Bishop (Ba3)
		assertEquals('N', board[5][6]); // Knight (Ng6)
	}

	@Test
	public void testPlacePiecesOnBoard_BlackPiecesPlacement() {
		char[][] board = new char[8][8];
		String blackPiecesInput = "Ke8,Rc4,Bc2,Bb8,Pf4";
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		assertEquals('k', board[7][4]); // King (Ke8)
		assertEquals('r', board[3][2]); // Rook (Rc4)
		assertEquals('b', board[1][2]); // Bishop (Bc2)
		assertEquals('b', board[7][1]); // Bishop (Bb8)
		assertEquals('p', board[3][5]); // Pawn (Pf4)
	}

	@Test
	public void testCalculateValidMoves_WhiteKingInCheckmate() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Rb6,Ng1,Bc2,Pc6";
		String blackPiecesInput = "Ke8,Qg3,Rd7,Nf1,Bb4,Ba6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		String validMoves = ChessBoard.calculateValidMoves(board, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), "Ke1");
		assertEquals("Checkmate!", validMoves);
	}

	@Test
	public void testCalculateValidMoves_BlackKingInCheckmate() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Qe7,Re3,Ra4,Ba3,Ng6";
		String blackPiecesInput = "Ke8,Rc4,Bc2,Bb8,Pf4";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		String validMoves = ChessBoard.calculateValidMoves(board, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), "Ke8");

		assertEquals("Checkmate!", validMoves);
	}

	@Test
	public void testHasOverlap_WithOverlap_ReturnsTrue() {
		String input = "Ke1,Ke2,Qe7";
		assertFalse(ChessBoard.hasOverlap(input));
	}

	@Test
	public void testHasOverlap_NoOverlap_ReturnsFalse() {
		String input = "Ke1,Qe7,Ra1";
		assertFalse(ChessBoard.hasOverlap(input));
	}

	@Test
	public void testCalculateValidMoves_Checkmate() {
		char[][] board = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = ' ';
			}
		}
		board[7][7] = 'K';
		board[6][6] = 'q';
		board[6][5] = 'r';
		List<String> whitePieces = List.of("Kh8");
		List<String> blackPieces = List.of("Kh8");
		String validMoves = ChessBoard.calculateValidMoves(board, whitePieces, blackPieces, "Kh8");
		assertEquals("Checkmate!", validMoves);
	}

	@Test
	public void testCloneBoard() {
		char[][] originalBoard = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' } };
		char[][] clonedBoard = ChessBoard.cloneBoard(originalBoard);
		assertArrayEquals(originalBoard, clonedBoard);
	}

	@Test
	public void testGetPieceToMove_ValidInput() {
		List<String> whitePieces = Arrays.asList("Ka1", "Qb2");
		List<String> blackPieces = Arrays.asList("Kc3", "Bd4");
		String userInput = "Ka1";
		String actualPieceToMove = new ChessBoard().getPieceToMove(new Scanner(userInput), whitePieces, blackPieces);
		String expectedPieceToMove = "Ka1";
		assertEquals(expectedPieceToMove, actualPieceToMove);
	}
}