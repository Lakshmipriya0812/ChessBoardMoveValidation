import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ChessBoardInputsTest {

	@Test
	public void testValidateInput_ValidInput() {
		System.out.println("Testing Valid Inputs ");
		assertTrue(ChessBoard.validateInput("Ke1,Qe7,Re3,Ra4,Ba3,Ng6"));
		assertTrue(ChessBoard.validateInput("Ke8,Rc4,Bc2,Bb8,Pf4"));
	}

	@Test
	public void testValidateInput_InvalidInput() {
		System.out.println("Testing Invalid Inputs ");
		assertFalse(ChessBoard.validateInput("Ke1,Qe7,Invalid"));
		assertFalse(ChessBoard.validateInput("Ke1,Qe7,Ng6,P"));
		assertTrue(ChessBoard.validateInput("Ke1,Qe7,Ng6"));
	}

	@Test
	public void testValidateInput_ValidInput_ReturnsTrue() {
		String input = "Ke1,Qe7,Ra1";
		assertTrue(ChessBoard.validateInput(input));
	}

	@Test
	public void testValidateInput_InvalidInput_ReturnsFalse() {
		String input = "Kk1,Qe9,Ra1";
		assertFalse(ChessBoard.validateInput(input));
	}

	@Test
	public void testConfirmMaxPieces_LessThanMaxPieces_ReturnsTrue() {
		String input = "Ke1,Qe7,Ra1";
		assertTrue(ChessBoard.confirmMaxPieces(input));
	}

	@Test
	public void testConfirmMaxPieces_MaxPieces_ReturnsFalse() {
		String input = "Ke1,Qe7,Ra1,Na2,Bb2,Nc1,Rb2,Nc3,Bd4,Ne5,Rf6,Ng7,Nh8,Bg1,Bh2,Pa3,Pb4,Pc5,Pd6,Pe7,Pf8";
		assertFalse(ChessBoard.confirmMaxPieces(input));
	}

	@Test
	public void testValidFormat_ValidFormat_ReturnsTrue() {
		String pieceToMove = "Ke1";
		assertTrue(ChessBoard.validFormat(pieceToMove));
	}

	@Test
	public void testValidFormat_InvalidFormat_ReturnsFalse() {
		String pieceToMove = "Ke";
		assertFalse(ChessBoard.validFormat(pieceToMove));
	}

	@Test
	public void testGetChessPieceSymbol() {
		assertEquals("♙", ChessBoard.getChessPieceSymbol('P'));
		assertEquals("♟", ChessBoard.getChessPieceSymbol('p'));
		assertEquals("♖", ChessBoard.getChessPieceSymbol('R'));
		assertEquals("♜", ChessBoard.getChessPieceSymbol('r'));
		assertEquals("♘", ChessBoard.getChessPieceSymbol('N'));
		assertEquals("♞", ChessBoard.getChessPieceSymbol('n'));
		assertEquals("♗", ChessBoard.getChessPieceSymbol('B'));
		assertEquals("♝", ChessBoard.getChessPieceSymbol('b'));
		assertEquals("♕", ChessBoard.getChessPieceSymbol('Q'));
		assertEquals("♛", ChessBoard.getChessPieceSymbol('q'));
		assertEquals("♔", ChessBoard.getChessPieceSymbol('K'));
		assertEquals("♚", ChessBoard.getChessPieceSymbol('k'));
		assertEquals(" ", ChessBoard.getChessPieceSymbol('\u0000'));
	}

	@Test
	public void testParseValidMoves() {
		String validMoves = "a1,b2,c3,d4,e5";
		Set<String> parsedMoves = ChessBoard.parseValidMoves(validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("a1");
		expectedMoves.add("b2");
		expectedMoves.add("c3");
		expectedMoves.add("d4");
		expectedMoves.add("e5");
		assertEquals(expectedMoves.size(), parsedMoves.size());
		assertTrue(parsedMoves.containsAll(expectedMoves));
	}

	@Test
	public void testCalculateValidMoves_DefaultInput() {
		char[][] board = new char[8][8];
		List<String> whitePieces = List.of("Kg1", "Qd4");
		List<String> blackPiece = List.of("Ka1", "Qh4");
		String pieceToMove = "Xx0";
		String validMoves = ChessBoard.calculateValidMoves(board, whitePieces, blackPiece, pieceToMove);
		assertEquals("", validMoves);
	}

	@Test
	public void testPieceToMoveValidation() {
		List<String> whitePieces = Arrays.asList("Pc2", "Kc3", "Qd4", "Ra5", "Nb6", "Bc7");
		List<String> blackPieces = Arrays.asList("Pd7", "Ke8", "Qf8", "Rg8", "Nh8", "Bi8");
		String input = "PIECE TO MOVE: Pc2";
		String pieceToMove = input.substring(input.indexOf(":") + 2).trim();
		assertTrue(whitePieces.contains(pieceToMove) || blackPieces.contains(pieceToMove));
	}

	@Test
	public void testWhitePiecesInputValidation() {
		String input = "Pc2,Kc3,Qd4,Ra5,Nb6,Pd8";
		boolean isValid = ChessBoard.validateInput(input);
		System.out.println("Input: " + input);
		System.out.println("isValid: " + isValid);
		assertTrue(isValid);
	}

	@Test
	public void testGetPieceToMove() {
		Scanner scanner = new Scanner("Ka1\n");
		List<String> whitePieces = new ArrayList<>();
		whitePieces.add("Ka1");
		List<String> blackPieces = new ArrayList<>();
		ChessBoard chessBoard = new ChessBoard();
		String pieceToMove = chessBoard.getPieceToMove(scanner, whitePieces, blackPieces);
		assertEquals("Ka1", pieceToMove);
	}

	@Test
	public void testWhitePiecesInputLoop() {
		List<String> validInput = Arrays.asList("Ka2", "Qb3", "Bc4", "Rd5", "Pe6", "Pf7", "Pg8", "Re6");
		assertEquals(false, ChessBoard.validateInput(validInput.toString()));
	}

	@Test
	public void testBlackPiecesInputLoop() {
		List<String> validInput = Arrays.asList("Ka7", "Bb6", "Qb6", "Rd4", "Pe3");
		assertEquals(false, ChessBoard.validateInput(validInput.toString()));
	}

}
