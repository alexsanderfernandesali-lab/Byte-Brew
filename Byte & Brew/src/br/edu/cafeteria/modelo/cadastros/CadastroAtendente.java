package br.edu.cafeteria.modelo.cadastros;

import java.util.ArrayList;
import br.edu.cafeteria.modelo.atendente.*;

public class CadastroAtendente {
    private ArrayList<Atendente> listaAtendentes;
    
    public CadastroAtendente() {
        this.listaAtendentes = new ArrayList<>();
    }
    
 
    
    public void adicionarAtendente(Atendente atendente) {
        listaAtendentes.add(atendente);
        System.out.println("Atendente " + atendente.getNome() + " cadastrado!");
    }
    
    public void adicionarAtendente(String nome) {
        Atendente novoAtendente = new Atendente(nome);
        listaAtendentes.add(novoAtendente);
        System.out.println("Atendente " + nome + " cadastrado!");
    }
    
    public Atendente buscarPorId(int id) {
        for (Atendente a : listaAtendentes) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
    
    public Atendente buscarPorNome(String nome) {
        for (Atendente a : listaAtendentes) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }
    
    public void listarAtendentes() {
        if (listaAtendentes.isEmpty()) {
            System.out.println("Nenhum atendente cadastrado.");
            return;
        }
        System.out.println("LISTA DE ATENDENTES:");
        for (Atendente a : listaAtendentes) {
            System.out.println("   " + a);
        }
    }
    
    public ArrayList<Atendente> getListaAtendentes() {
        return listaAtendentes;
    }
    
    public int getTotalAtendentes() {
        return listaAtendentes.size();
    }
}