package br.com.jlucasguedes;

import java.util.Scanner;

public class AlgoritimoCesar {
    static final char[] alfabeto = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String args[]) {

        String textoDecifrado = "";
        String textoCifrado = "";
        Scanner inputNumerico = new Scanner(System.in);
        System.out.print("Digite a chave: ");
        int chave = inputNumerico.nextInt();

        Scanner inputString = new Scanner(System.in);
        System.out.print("Digite o texto a ser crifrado: ");
        String textoOriginal = inputString.nextLine();

        System.out.print("Texto Original: " + textoOriginal + "\n");
        textoCifrado = cifrar(textoOriginal, chave);
        System.out.print("Texto cifrado: ");
        System.out.print(textoCifrado);


        System.out.println("\n\n\n\n1 - Escolha uma opção decifrar sabendo a chave: ");
        System.out.println("2 - Escolha uma opção decifrar com força bruta: ");
        System.out.print("opção: ");
        int opcao = inputNumerico.nextInt();


        switch (opcao) {
            case 1: {
                textoDecifrado = decifrar(textoCifrado, chave);
                System.out.print("\nTexto decifrado: ");
                System.out.print(textoDecifrado);
                char a = 'A';
                break;
            }
            case 2: {
                for (int i = 0; i < 26; i++) {
                    System.out.println("Chave usada: " + i + " || Texto possível: " + AlgoritimoCesar.decifrar(textoCifrado, i));
                }
                break;
            }
            default:
                System.out.println("Opção inválida!!!");
        }

    }

    public static String cifrar(String texto, int chave) {
        String resultado = "";
        boolean encontrado;
        for (int i = 0; i < texto.length(); i++) {
            encontrado = false;
            for (int j = 0; j < alfabeto.length; j++) {
                if (texto.toUpperCase().charAt(i) == alfabeto[j]) {
                    resultado += alfabeto[(j + chave) % 26];
                    encontrado = true;
                }
            }
            if (!encontrado) {
                resultado += texto.charAt(i);
            }
        }
        return resultado;
    }

    public static String decifrar(String texto, int chave) {
        String resultado = "";
        boolean encontrado;
        for (int i = 0; i < texto.length(); i++) {
            encontrado = false;
            for (int j = 0; j < alfabeto.length; j++) {
                if (texto.toUpperCase().charAt(i) == alfabeto[j]) {
                    int num = (j - chave);
                    resultado += alfabeto[(num + 26) % 26];
                    encontrado = true;
                }
            }
            if (!encontrado) {
                resultado += texto.toUpperCase().charAt(i);
            }
        }
        return resultado;
    }
}
