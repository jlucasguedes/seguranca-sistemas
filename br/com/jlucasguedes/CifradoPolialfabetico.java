package br.com.jlucasguedes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CifradoPolialfabetico {

    static final char[] alfabeto = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String args[]) {

        String textoCifrado = "";
        String textoDecifrado = "";

        Scanner inputNumerico = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);

        System.out.print("Digite a chave: ");
        String chave = inputString.nextLine();
        List<Integer> posicaoChave = pegarPosicaoChave(chave);

        System.out.print("Digite o texto a ser crifrado: ");
        String textoOriginal = inputString.nextLine();

        System.out.println("\n=================================================");
        System.out.print("Texto Original: " + textoOriginal.toUpperCase() + "\n");
        String textoOriginalDividido = divideString(divideStringEmBLocos(textoOriginal.trim(), chave.length()));
        System.out.print("Texto Original Dividido: " + textoOriginalDividido.toUpperCase() + "\n");

        List<String> blocos = divideStringEmBLocos(textoOriginal.trim(), chave.length());

        for (String str : blocos) {
            for (int j = 0; j < posicaoChave.size(); j++) {
                if (j < str.length()) {
                    textoCifrado += AlgoritimoCesar.cifrar(str.toUpperCase().charAt(j) + "", posicaoChave.get(j));
                }
            }
        }

        System.out.println("=================================================");
        System.out.print("Texto cifrado: " + textoCifrado + "\n");

        String textoCifradoDividido = divideString(divideStringEmBLocos(textoCifrado.trim(), chave.length()));
        System.out.print("Texto cifrado Dividido: " + textoCifradoDividido + "\n");

        System.out.println("\n\n1 - Escolha uma opção decifrar sabendo a chave: ");
        System.out.print("opção: ");
        int opcao = inputNumerico.nextInt();


        switch (opcao) {
            case 1: {
                for (String str : divideStringEmBLocos(textoCifrado, chave.length())) {
                    for (int j = 0; j < posicaoChave.size(); j++) {
                        if (j < str.length()) {
                            textoDecifrado += AlgoritimoCesar.decifrar(str.toUpperCase().charAt(j) + "", posicaoChave.get(j));
                        }
                    }
                }
                System.out.println("\n=================================================");
                System.out.print("Texto decifrado: " + textoDecifrado + "\n");
                String textoDecifradoDividido = divideString(divideStringEmBLocos(textoDecifrado.trim(), chave.length()));
                System.out.print("Texto decifrado Dividido: " + textoDecifradoDividido + "\n");
                break;
            }
            default:
                System.out.println("Opção inválida!!!");
        }

    }

    public static List<Integer> pegarPosicaoChave(String chave) {
        List<Integer> posicaoChave = new ArrayList<>();

        for (char caracterChave : chave.toUpperCase().toCharArray()) {
            for (int i = 0; i < alfabeto.length; i++) {
                if (caracterChave == alfabeto[i]) {
                    posicaoChave.add(i);
                }
            }
        }

        return posicaoChave;
    }

    public static List<String> divideStringEmBLocos(String str, int quantidadeLetras) {
        List<String> blocos = new ArrayList<>();
        int strLength = str.length();

        for (int i = 0; i < strLength; i += quantidadeLetras) {
            int endIndex = Math.min(i + quantidadeLetras, strLength);
            blocos.add(str.substring(i, endIndex));
        }
        return blocos;
    }

    public static String divideString(List<String> blocos) {
        String mensagemDividida = "";
        for (int i = 0; i < blocos.size(); i++) {
            if (i == (blocos.size() - 1)) {
                mensagemDividida += blocos.get(i);
            } else {
                mensagemDividida += blocos.get(i) + ".";
            }
        }
        return mensagemDividida;
    }

}
