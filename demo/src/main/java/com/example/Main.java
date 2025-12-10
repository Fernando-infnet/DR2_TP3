package com.example;

import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        
        User userFernando = new User(UUID.randomUUID(), "Fernando", "fernando.dev@gmail.com", "Desenvolvedor");
        User userAna = new User(UUID.randomUUID(), "Ana", "ana.qa@gmail.com", "Testadora");

        Task taskImplementarLogin = new Task("Implementar Tela de Login", "Criar o endpoint");
        taskImplementarLogin.assignUser(userFernando);

        Task taskEscreverTestes = new Task("Escrever Testes", "Escrever testes");
        taskEscreverTestes.assignUser(userAna);

        Sprint sprintPlanejamento = new Sprint("Planejamento Inicial", LocalDate.now(), LocalDate.now().plusDays(14));
        sprintPlanejamento = sprintPlanejamento.withTaskAdded(taskImplementarLogin);
        sprintPlanejamento = sprintPlanejamento.withTaskAdded(taskEscreverTestes);

        Project projectPrincipal = new Project("Sistema de Gestão de Projetos", "Ferramenta para gerenciamento ágil de projetos e tarefas.");
        projectPrincipal.addSprint(sprintPlanejamento);

        Item itemMouse = new Item("MOUSEC9", 3, 70);
        Item itemTeclado = new Item("KEYBOARD12", 2, 60);

        System.out.println("Projeto: " + projectPrincipal.getName());
        for (Sprint s : projectPrincipal.listSprints()) {
            System.out.println("  Sprint: " + s.getName());
            for (Task t : s.listTasks()) {
                System.out.println("    Tarefa: " + t.showDetails());
            }
        }

        System.out.println("\nItens de Estoque:");
        System.out.println("  " + itemMouse);
        System.out.println("  " + itemTeclado);

        Item updatedItemMouse = itemMouse.updateQuantity(5);
        System.out.println("\nApós atualizar a quantidade:");
        System.out.println("  Original (Mouse): " + itemMouse);
        System.out.println("  Atualizado (Mouse): " + updatedItemMouse);

        Sprint updatedSprint = sprintPlanejamento.withTaskRemoved(taskImplementarLogin);
        System.out.println("\nTarefas do Sprint após remover uma tarefa:");
        System.out.println("  Tarefas do Sprint Original:");
        sprintPlanejamento.listTasks().forEach(t -> System.out.println("    " + t.showDetails()));
        System.out.println("  Tarefas do Novo Sprint:");
        updatedSprint.listTasks().forEach(t -> System.out.println("    " + t.showDetails()));
    }
}
