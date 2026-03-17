import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++;
            resposta += vetor[i]%2;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++; // Operador ++
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa...
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }

    static double nanoToMilli(double nano){
        return nanoToMilli * nano;
    }

    static void salvarResultado(String arquivo){
        
    }
    public static void main(String[] args) {
        int[] vetor;
        StringBuilder anotacoes = new StringBuilder();
        anotacoes.append("Tempo; Tamanho; Cont Operações; Algoritmo\n");

        for(int i = 0; i < tamanhosTesteGrande.length; i++){
            String res;
            vetor = gerarVetor(tamanhosTesteGrande[i]);
            operacoes = 0;
            long inicio = (long) nanoToMilli(System.nanoTime());
            long termino;  
            codigo1(vetor);
            termino = (long) nanoToMilli(System.nanoTime());
            double tempo = (termino - inicio);
            res = String.format("%.4f;%d;%d;%d\n", tempo,tamanhosTesteGrande[i],operacoes,1);
            System.out.printf(res);
            anotacoes.append(res);
        }

        for(int i = 0; i < tamanhosTesteGrande.length; i++){
            String res;
            vetor = gerarVetor(tamanhosTesteGrande[i]);
            operacoes = 0;
            long inicio = (long) nanoToMilli(System.nanoTime());
            long termino;  
            codigo2(vetor);
            termino = (long) nanoToMilli(System.nanoTime());
            double tempo = (termino - inicio);
            res = String.format("%.4f;%d;%d;%d\n", tempo,tamanhosTesteGrande[i],operacoes,2);
            System.out.printf(res);
            anotacoes.append(res);
        }

        for(int i = 0; i < tamanhosTesteMedio.length; i++){
            String res;
            vetor = gerarVetor(tamanhosTesteMedio[i]);
            operacoes = 0;
            long inicio = (long) nanoToMilli(System.nanoTime());
            long termino;  
            codigo3(vetor);
            termino = (long) nanoToMilli(System.nanoTime());
            double tempo = (termino - inicio);
            res = String.format("%.4f;%d;%d;%d\n", tempo,tamanhosTesteMedio[i],operacoes,3);
            System.out.printf(res);
            anotacoes.append(res);
        }


        int n = 3;

        if (n <= tamanhosTestePequeno.length) {
            for(int i = 0; i < n; i++){
                String res;
                operacoes = 0;
                long inicio = (long) nanoToMilli(System.nanoTime());
                long termino;  
                codigo4(tamanhosTestePequeno[i]);
                termino = (long) nanoToMilli(System.nanoTime());
                double tempo = (termino - inicio);
                res = String.format("%.4f;%d;%d;%d\n", tempo,tamanhosTestePequeno[i],operacoes,4);
                anotacoes.append(res);
            }
        }
        
        try{
            FileWriter file = new FileWriter("Tabela.csv");
            file.write(anotacoes.toString());
            file.close();
        }
        catch(IOException e){
            e.getStackTrace();
        }
        



    }
}
