package com.sabo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> positionsUnderAttack = new ArrayList<String>();

        while(true){
            System.out.print("Which chess piece?\n(p) - pawn\n(r) - rook\n(b) - bishop\n(n) - knight\n(q) - queen\n(k) - king\n\n(x) - quit\n\n");
            char chessPiece = scanner.nextLine().toCharArray()[0];
            if(chessPiece == 'x') break;

            System.out.print("Which position is the piece on? (ex. 3B)\n\n");
            String position = scanner.nextLine().toLowerCase();
            int horizontal = Character.getNumericValue(position.toCharArray()[0])-1;
            int vertical = position.toCharArray()[1] - 97;

            if(horizontal < 0 || horizontal > 7 || vertical < 0 || vertical > 7) positionsUnderAttack.add("Incorrect input");
            else{
                switch (chessPiece){
                    case 'p': positionsUnderAttack = pawnAttacks(horizontal, vertical); break;
                    case 'r': positionsUnderAttack = rookAttacks(horizontal, vertical); break;
                    case 'b': positionsUnderAttack = bishopAttacks(horizontal, vertical); break;
                    case 'n': positionsUnderAttack = knightAttacks(horizontal, vertical); break;
                    case 'q': positionsUnderAttack = queenAttacks(horizontal, vertical); break;
                    case 'k': positionsUnderAttack = kingAttacks(horizontal, vertical); break;
                    default: positionsUnderAttack.add("Incorrect input");
                }
            }

            positionsUnderAttack.forEach((attackedPosition) -> System.out.print(attackedPosition + "  "));
            positionsUnderAttack.clear();
            System.out.print("\n\n");
        }

    }

    private static String formatOutput(int horizontal, int vertical){
        String output = Integer.toString(horizontal+1) + (char)(vertical+97);
        return output.toUpperCase();
    }

    private static ArrayList<String> pawnAttacks(int horizontal, int vertical){
        ArrayList<String> positions = new ArrayList<String>();
        positions.add(formatOutput(horizontal, vertical));
        positions.add("  white:");
        if(vertical < 7) {
            if(horizontal > 0) positions.add(formatOutput(horizontal - 1, vertical + 1));
            if(horizontal < 7) positions.add(formatOutput(horizontal + 1, vertical + 1));
        }
        positions.add("  black:");
        if(vertical > 0) {
            if(horizontal > 0) positions.add(formatOutput(horizontal - 1, vertical - 1));
            if(horizontal < 7) positions.add(formatOutput(horizontal + 1, vertical - 1));
        }
        return positions;
    }

    private static ArrayList<String> rookAttacks(int horizontal, int vertical) {
        ArrayList<String> positions = new ArrayList<String>();
        for(int i = 0; i<8; i++){
            positions.add(formatOutput(i, vertical));
        }
        for(int i = 0; i<8; i++){
            if(i != vertical)positions.add(formatOutput(horizontal, i));
        }
        return positions;
    }

    private static ArrayList<String> bishopAttacks(int horizontal, int vertical) {
        ArrayList<String> positions = new ArrayList<String>();
        int initialHorizontal = horizontal;
        int initialVertical = vertical;

        while(horizontal > 0 && vertical > 0){
            horizontal --;
            vertical --;
        }
        while(horizontal < 8 && vertical < 8){
            positions.add(formatOutput(horizontal, vertical));
            horizontal++;
            vertical++;
        }

        horizontal = initialHorizontal;
        vertical = initialVertical;

        while(horizontal < 7 && vertical > 0){
            horizontal ++;
            vertical --;
        }
        while(horizontal > -1 && vertical < 8){
            if(horizontal != initialHorizontal)positions.add(formatOutput(horizontal, vertical));
            horizontal--;
            vertical++;
        }

        return positions;
    }

    private static ArrayList<String> knightAttacks(int horizontal, int vertical) {
        ArrayList<String> positions = new ArrayList<String>();
        if(vertical-1 > -1){
            if(vertical-2 > -1){
                if(horizontal-2>=0)positions.add(formatOutput(horizontal-1, vertical-2));
                if(horizontal+2<=7)positions.add(formatOutput(horizontal+1, vertical-2));
            }
            if(horizontal-2>=0)positions.add(formatOutput(horizontal-2, vertical-1));
            if(horizontal+2<=7)positions.add(formatOutput(horizontal+2, vertical-1));
        }
        positions.add(formatOutput(horizontal, vertical));
        if(vertical+1 < 8){
            if(horizontal-2>=0)positions.add(formatOutput(horizontal-2, vertical+1));
            if(horizontal+2<=7)positions.add(formatOutput(horizontal+2, vertical+1));
            if(vertical+2 < 8){
                if(horizontal-1>=0)positions.add(formatOutput(horizontal-1, vertical+2));
                if(horizontal+1<=7)positions.add(formatOutput(horizontal+1, vertical+2));
            }
        }
        return positions;
    }

    private static ArrayList<String> queenAttacks(int horizontal, int vertical) {
        ArrayList<String> positions = new ArrayList<String>();
        positions.addAll(rookAttacks(horizontal, vertical));
        positions.addAll(bishopAttacks(horizontal, vertical));
        positions.remove(formatOutput(horizontal,vertical));
        return positions;
    }

    private static ArrayList<String> kingAttacks(int horizontal, int vertical) {
        ArrayList<String> positions = new ArrayList<String>();
        if(vertical+1 < 8){
            if (horizontal-1 > -1) positions.add(formatOutput(horizontal-1, vertical+1));
            positions.add(formatOutput(horizontal, vertical+1));
            if (horizontal+1 < 8) positions.add(formatOutput(horizontal+1, vertical+1));
        }
        if (horizontal-1 > -1) positions.add(formatOutput(horizontal-1, vertical));
        positions.add(formatOutput(horizontal, vertical));
        if (horizontal+1 < 8) positions.add(formatOutput(horizontal+1, vertical));
        if(vertical-1 > -1){
            if (horizontal-1 > -1) positions.add(formatOutput(horizontal-1, vertical-1));
            positions.add(formatOutput(horizontal, vertical-1));
            if (horizontal+1 < 8) positions.add(formatOutput(horizontal+1, vertical-1));
        }
        return positions;
    }
}
