import java.util.*;

public class ChessBoard {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in, "UTF-8");

		char[][] gameBoard = new char[8][8];
		List<String> whitePieces = new ArrayList<>();
		List<String> blackPieces = new ArrayList<>();

		System.out.println("Welcome to Chess Board Move Validation :)");

		String whitePiecesInput = getPlayerPiecesInput(scanner, "white");
		while (!validateInput(whitePiecesInput) || !oneKing(whitePiecesInput, 'K')
				|| !confirmMaxPieces(whitePiecesInput) || hasOverlap(whitePiecesInput)) {
			System.out.println("Invalid input format. Please enter again.");
			whitePiecesInput = getPlayerPiecesInput(scanner, "white");
		}
		placePiecesOnBoard(gameBoard, whitePiecesInput, true);
		whitePieces.addAll(Arrays.asList(whitePiecesInput.split(",")));

		String blackPiecesInput = getPlayerPiecesInput(scanner, "black");
		while (!validateInput(blackPiecesInput) || !oneKing(blackPiecesInput, 'k')
				|| !confirmMaxPieces(blackPiecesInput) || hasOverlap(blackPiecesInput)) {
			System.out.println("Invalid input format. Please enter again.");
			blackPiecesInput = getPlayerPiecesInput(scanner, "black");
		}
		placePiecesOnBoard(gameBoard, blackPiecesInput, false);
		blackPieces.addAll(Arrays.asList(blackPiecesInput.split(",")));

		System.out.print("PIECE TO MOVE: ");
		String pieceToMove = scanner.nextLine();

		while (!validFormat(pieceToMove) || !(whitePieces.contains(pieceToMove) || blackPieces.contains(pieceToMove))) {
			System.out.println("Please try again.");
			System.out.print("PIECE TO MOVE: ");
			pieceToMove = scanner.nextLine();
		}
		printBoard(gameBoard); // Print the board after placing pieces
		String validMoves = calculateValidMoves(gameBoard, whitePieces, blackPieces, pieceToMove);
		System.out.println("LEGAL MOVES FOR " + pieceToMove + ": " + validMoves);
		System.out.println("Chess Board Legal Moves");
		printBoardWithValidMoves(gameBoard, parseValidMoves(validMoves));

		scanner.close();
	}

	public static Set<String> parseValidMoves(String validMoves) {
		Set<String> movesSet = new HashSet<>();
		String[] movesArray = validMoves.split(",");
		for (String move : movesArray) {
			movesSet.add(move);
		}
		return movesSet;
	}

	public static String getPlayerPiecesInput(Scanner scanner, String playerColor) {
		System.out.print("Enter " + playerColor + " pieces: ");
		return scanner.nextLine();
	}

	public static void placePiecesOnBoard(char[][] board, String piecesInput, boolean isWhite) {
		String[] piecesArray = piecesInput.split(",");
		for (String piece : piecesArray) {
			placePiece(board, piece, isWhite);
		}
	}

	public static boolean hasOverlap(String input) {
		Set<String> occupiedSpaces = new HashSet<>();
		String[] pieces = input.split(",");
		for (String piece : pieces) {
			String location = piece.substring(1);
			if (!occupiedSpaces.add(location)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateInput(String input) {
		String[] pieces = input.split(",");
		for (String piece : pieces) {
			if (piece.length() != 3) {
				return false;
			}
			char type = piece.charAt(0);
			char column = piece.charAt(1);
			char row = piece.charAt(2);
			if (!(type == 'K' || type == 'Q' || type == 'R' || type == 'B' || type == 'N' || type == 'P')) {
				return false;
			}
			if (!(column >= 'a' && column <= 'h') || !(row >= '1' && row <= '8')) {
				return false;
			}
			if (!Character.isLetter(column) || !Character.isDigit(row)) {
				return false;
			}
		}
		return true;
	}

	public static boolean oneKing(String input, char kingType) {
		String[] pieces = input.split(",");
		int kings = 0;
		for (String piece : pieces) {
			if (piece.charAt(0) == kingType || piece.charAt(0) == Character.toUpperCase(kingType)) {
				kings++;
			}
		}
		return kings == 1;
	}

	public static boolean confirmMaxPieces(String input) {
		String[] pieces = input.split(",");
		return pieces.length <= 16;
	}

	public static boolean validFormat(String pieceToMove) {
		return (pieceToMove.length() == 3);
	}

	public static void placePiece(char[][] board, String piece, boolean isWhite) {
		char type = piece.charAt(0);
		char column = piece.charAt(1);
		char row = piece.charAt(2);
		int x = row - '1';
		int y = column - 'a';
		board[x][y] = isWhite ? type : Character.toLowerCase(type);
	}

	public static String calculateValidMoves(char[][] board, List<String> whitePieces, List<String> blackPieces,
			String pieceToMove) {
		int row = pieceToMove.charAt(2) - '1';
		int col = pieceToMove.charAt(1) - 'a';
		char pieceType = pieceToMove.charAt(0);
		boolean isWhite = whitePieces.contains(pieceToMove);

		List<String> validMoves = ChessPieceValidator.calculateLegalMoves(board, pieceType, row, col, isWhite);

		if (ChessPieceValidator.isKingInCheck(board, isWhite)) {
			// If in check, check for checkmate
			boolean isCheckmate = true;
			for (String move : validMoves) {
				char[][] testBoard = cloneBoard(board);
				int newRow = move.charAt(1) - '1';
				int newCol = move.charAt(0) - 'a';
				testBoard[newRow][newCol] = pieceType;
				testBoard[row][col] = '\u0000';
				if (!ChessPieceValidator.isKingInCheck(testBoard, isWhite)) {
					isCheckmate = false;
					break;
				}
			}
			// If it's checkmate, return appropriate message
			if (isCheckmate) {
				return ("Checkmate!");
			}
		}
		// Check for castling moves
		if (pieceType == 'K' || pieceType == 'k') {
			validMoves.addAll(getCastlingMoves(board, isWhite));
		}
		// Promotion for pawn
		if (Character.toUpperCase(pieceType) == 'P') {
			validMoves.addAll(getPawnPromotionMoves(row, isWhite));
		}
		// Remove duplicates
		Set<String> uniqueMoves = new HashSet<>(validMoves);
		validMoves.clear();
		validMoves.addAll(uniqueMoves);
		StringBuilder validMovesString = new StringBuilder();
		for (String move : validMoves) {
			validMovesString.append(move).append(",");
		}
		if (validMovesString.length() > 0) {
			validMovesString.deleteCharAt(validMovesString.length() - 1); // Remove the last comma
		}
		return validMovesString.toString();
	}

	protected static List<String> getCastlingMoves(char[][] board, boolean isWhite) {
		List<String> castlingMoves = new ArrayList<>();
		int row = isWhite ? 0 : 7;

		// King-side castling
		if (canCastleKingSide(board, row, isWhite)) {
			castlingMoves.add(isWhite ? "g1" : "g8");
		}

		// Queen-side castling
		if (canCastleQueenSide(board, row, isWhite)) {
			castlingMoves.add(isWhite ? "c1" : "c8");
		}

		return castlingMoves;
	}

	protected static boolean canCastleKingSide(char[][] board, int row, boolean isWhite) {
		char king = isWhite ? 'K' : 'k';
		char rook = isWhite ? 'R' : 'r';

		// Check if king and rook haven't moved
		if (board[row][4] != king || board[row][7] != rook) {
			return false;
		}

		// Check if squares between king and rook are empty
		for (int col = 5; col <= 6; col++) {
			if (board[row][col] != '\u0000') {
				return false;
			}
		}

		// Check if squares the king moves through are not under attack
		if (ChessPieceValidator.isSquareUnderAttack(board, row, 4, !isWhite)) {
			return false;
		} else {
			if (!ChessPieceValidator.isSquareUnderAttack(board, row, 5, !isWhite)
					&& !ChessPieceValidator.isSquareUnderAttack(board, row, 6, !isWhite)) {
				return true;
			}
			return false;
		}

	}

	protected static boolean canCastleQueenSide(char[][] board, int row, boolean isWhite) {
		char king = isWhite ? 'K' : 'k';
		char rook = isWhite ? 'R' : 'r';

		// Check if king and rook haven't moved
		if (board[row][4] != king || board[row][0] != rook) {
			return false;
		}

		// Check if squares between king and rook are empty
		for (int col = 1; col <= 3; col++) {
			if (board[row][col] != '\u0000') {
				return false;
			}
		}

		// Check if squares the king moves through are not under attack
		if (ChessPieceValidator.isSquareUnderAttack(board, row, 4, !isWhite)) {
			return false;
		} else if (ChessPieceValidator.isSquareUnderAttack(board, row, 3, !isWhite)
				|| ChessPieceValidator.isSquareUnderAttack(board, row, 2, !isWhite)) {
			return false;
		}

		return true;
	}

	protected static List<String> getPawnPromotionMoves(int row, boolean isWhite) {
		List<String> promotionMoves = new ArrayList<>();
		int promotionRow = isWhite ? 7 : 0;

		// If the pawn has reached the promotion row, add promotion options
		if (row == promotionRow) {
			char[] promotionPieces = { 'Q', 'R', 'B', 'N' };
			for (char piece : promotionPieces) {
				promotionMoves.add(" Pawn Can Change to " + piece);
			}
		}

		return promotionMoves;
	}

	protected static char[][] cloneBoard(char[][] board) {
		char[][] clone = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				clone[i][j] = board[i][j];
			}
		}
		return clone;
	}

	public static void printBoard(char[][] board) {
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int row = 7; row >= 0; row--) {
			System.out.print((row + 1) + " |");
			for (int col = 0; col < 8; col++) {
				System.out.print(" " + getChessPieceSymbol(board[row][col]) + " |");
			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+");
		}
		System.out.println("    a   b   c   d   e   f   g   h");

	}

	public static String getChessPieceSymbol(char piece) {
		switch (piece) {
		case 'P':
			return "♙"; // White Pawn
		case 'p':
			return "♟"; // Black Pawn
		case 'R':
			return "♖"; // White Rook
		case 'r':
			return "♜"; // Black Rook
		case 'N':
			return "♘"; // White Knight
		case 'n':
			return "♞"; // Black Knight
		case 'B':
			return "♗"; // White Bishop
		case 'b':
			return "♝"; // Black Bishop
		case 'Q':
			return "♕"; // White Queen
		case 'q':
			return "♛"; // Black Queen
		case 'K':
			return "♔"; // White King
		case 'k':
			return "♚"; // Black King
		default:
			return " ";
		}
	}

	public static void printBoardWithValidMoves(char[][] board, Set<String> validMoves) {
		System.out.println("  +---+---+---+---+---+---+---+---+");
		for (int row = 7; row >= 0; row--) {
			System.out.print((row + 1) + " |");
			for (int col = 0; col < 8; col++) {
				String position = "" + (char) ('a' + col) + (char) ('1' + row);
				if (validMoves.contains(position)) {
					System.out.print(" " + "\u001b[33m*" + "\u001b[0m" + " |");
				} else {
					System.out.print(" " + getChessPieceSymbol(board[row][col]) + " |");
				}
			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+");
		}
		System.out.println("    a   b   c   d   e   f   g   h");
	}

	public String getPieceToMove(Scanner scanner, List<String> whitePieces, List<String> blackPieces) {
		System.out.print("PIECE TO MOVE: ");
		String pieceToMove = scanner.nextLine();
		while (!validFormat(pieceToMove) || !(whitePieces.contains(pieceToMove) || blackPieces.contains(pieceToMove))) {
			System.out.println("Please try again.");
			System.out.print("PIECE TO MOVE: ");
			pieceToMove = scanner.nextLine();
		}
		return pieceToMove;
	}

}