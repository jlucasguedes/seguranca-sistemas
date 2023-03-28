package br.com.jlucasguedes;

import java.util.*;

public class PlayFair {
    static final List<Character> alfabetoList = new ArrayList<>(Arrays.asList(new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}));

    public static void main(String[] args) {
        Scanner inputString = new Scanner(System.in);
        System.out.print("Digite a chave: ");
        String chave = inputString.nextLine();

        System.out.print("Digite a palavra a ser cifrada: ");
        String textoOriginal = inputString.nextLine();

        Character[][] matriz = gerarMatriz(chave);
        List<Posicao> posicoes = getPosicoesNaMatriz(chave, textoOriginal);

        System.out.println();
        System.out.println("Texto dividido: " + divideStringEmBLocos(textoOriginal));
        String textoCifrado = cifrar(matriz, posicoes);
        System.out.println("Texto cifrado: " + textoCifrado);

        List<Posicao> posicoesCifradas = getPosicoesNaMatriz(chave, textoCifrado);
        System.out.println("Texto decifrado: " + removerX(decifrar(matriz, posicoesCifradas)));
    }


    public static String cifrar(Character[][] matriz, List<Posicao> posicoes) {
        String palavraCifrada = "";
        for (int i = 0; i < posicoes.size(); i += 2) {
            if (posicoes.get(i).getLinha() == posicoes.get(i + 1).getLinha()) {
                if (posicoes.get(i).getColuna() == 4) {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][0];
                } else {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][posicoes.get(i).getColuna() + 1];
                }

                if (posicoes.get(i + 1).getColuna() == 4) {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][0];
                } else {
                    palavraCifrada += matriz[posicoes.get(i + 1).getLinha()][posicoes.get(i + 1).getColuna() + 1];

                }
            } else if (posicoes.get(i).getColuna() == posicoes.get(i + 1).getColuna()) {
                if (posicoes.get(i).getLinha() == 4) {
                    palavraCifrada += matriz[0][posicoes.get(i).getColuna()];
                } else {
                    palavraCifrada += matriz[posicoes.get(i).getLinha() + 1][posicoes.get(i).getColuna()];
                }

                if (posicoes.get(i + 1).getLinha() == 4) {
                    palavraCifrada += matriz[0][posicoes.get(i).getColuna()];
                } else {
                    palavraCifrada += matriz[posicoes.get(i + 1).getLinha() + 1][posicoes.get(i + 1).getColuna()];
                }
            } else {
                palavraCifrada += matriz[posicoes.get(i).getLinha()][posicoes.get(i + 1).getColuna()];
                palavraCifrada += matriz[posicoes.get(i + 1).getLinha()][posicoes.get(i).getColuna()];
            }
        }

        return palavraCifrada;
    }

    public static String decifrar(Character[][] matriz, List<Posicao> posicoes) {
        String palavraCifrada = "";
        for (int i = 0; i < posicoes.size(); i += 2) {
            if (posicoes.get(i).getLinha() == posicoes.get(i + 1).getLinha()) {
                if (posicoes.get(i).getColuna() == 0) {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][4];
                } else {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][posicoes.get(i).getColuna() - 1];
                }

                if (posicoes.get(i + 1).getColuna() == 0) {
                    palavraCifrada += matriz[posicoes.get(i).getLinha()][4];
                } else {
                    palavraCifrada += matriz[posicoes.get(i + 1).getLinha()][posicoes.get(i + 1).getColuna() - 1];

                }
            } else if (posicoes.get(i).getColuna() == posicoes.get(i + 1).getColuna()) {
                if (posicoes.get(i).getLinha() == 4) {
                    palavraCifrada += matriz[0][posicoes.get(i).getColuna()];
                } else {
                    palavraCifrada += matriz[posicoes.get(i).getLinha() - 1][posicoes.get(i).getColuna()];
                }

                if (posicoes.get(i + 1).getLinha() == 4) {
                    palavraCifrada += matriz[0][posicoes.get(i).getColuna()];
                } else {
                    palavraCifrada += matriz[posicoes.get(i + 1).getLinha() - 1][posicoes.get(i + 1).getColuna()];
                }
            } else {
                palavraCifrada += matriz[posicoes.get(i).getLinha()][posicoes.get(i + 1).getColuna()];
                palavraCifrada += matriz[posicoes.get(i + 1).getLinha()][posicoes.get(i).getColuna()];
            }
        }

        return palavraCifrada;
    }

    public static List<Posicao> getPosicoesNaMatriz(String chave, String textoOriginal) {
        Character[][] matriz = gerarMatriz(chave);
        List<String> blocos = divideStringEmBLocos(textoOriginal);
        List<Posicao> posicoes = new ArrayList<>();

        for (int i = 0; i < blocos.size(); i++) {
            for (int j = 0; j < blocos.get(i).length(); j++) {
                for (int linha = 0; linha < matriz.length; linha++) {
                    for (int coluna = 0; coluna < 5; coluna++) {
                        if (matriz[linha][coluna].charValue() == blocos.get(i).charAt(j)) {
                            posicoes.add(new Posicao(linha, coluna));
                        }
                    }
                }
            }
        }
        return posicoes;
    }

    public static List<String> divideStringEmBLocos(String palavra) {
        List<String> blocos = new ArrayList<>();
        int strLength = palavra.length();
        for (int i = 0; i < strLength; i += 2) {
            if ((strLength % 2) != 0) {
                palavra += "X";
                strLength += 1;
            }

            int endIndex = Math.min(i + 2, strLength);
            palavra = formatarLetraRepetida(palavra);
            blocos.add(palavra.substring(i, endIndex).toUpperCase());
        }
        return blocos;
    }

    public static String formatarLetraRepetida(String palavra) {
        palavra = palavra.toUpperCase();
        for (int i = 1; i < palavra.length(); i++) {
            if (palavra.charAt(i) == palavra.charAt(i - 1)) {
                if (palavra.charAt(i) == 'X') {
                    palavra = palavra.substring(0, i) + 'Z' + palavra.substring(i + 1);
                } else {
                    palavra = palavra.substring(0, i) + 'X' + palavra.substring(i + 1);
                }

            }
        }
        return palavra.toUpperCase();
    }

    public static Character[][] gerarMatriz(String chave) {
        Character matriz[][] = new Character[5][5];
        Character novoAlfabeto[] = formatarAlfabetoComChave(formatarLetraRepetida(chave)).toArray(new Character[25]);

        int posicao = 0;

        for (int linha = 0; linha < 5; linha++) {
            for (int coluna = 0; coluna < 5; coluna++) {
                matriz[linha][coluna] = novoAlfabeto[posicao];
                posicao++;
            }
        }

//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz.length; j++) {
//                System.out.print(matriz[i][j]);
//            }
//            System.out.println();
//        }

        return matriz;
    }

    public static List<Character> formatarAlfabetoComChave(String chave) {
        List<Character> chaveList = convertCharArrayToList(chave.toUpperCase());
        List<Character> novoAlfabeto = alfabetoList;
        int j = 0;

        for (Character ch : chaveList) {
            alfabetoList.remove(ch);
        }

        Collections.reverse(novoAlfabeto);
        Collections.reverse(chaveList);
        novoAlfabeto.addAll(chaveList);
        Collections.reverse(novoAlfabeto);

        return novoAlfabeto;
    }

    public static List<Character> convertCharArrayToList(String chave) {
        List<Character> listCharacter = new ArrayList<Character>();
        for (char ch : chave.toUpperCase().toCharArray()) {
            listCharacter.add(Character.valueOf(ch));
        }
        return listCharacter;
    }

    public static String removerX(String texto) {
        if (texto.charAt(texto.length() - 1) == 'X') {
            return texto.substring(0, texto.length() - 2) + texto.substring(texto.length() - 2, texto.length() - 1);
        }
        return texto;
    }
}
