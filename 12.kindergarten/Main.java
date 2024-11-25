package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Database db = new Database()) {
            DaycareSystem system = new DaycareSystem(db);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Меню управления детским садом ===");
                System.out.println("1. Добавить группу");
                System.out.println("2. Добавить ребенка");
                System.out.println("3. Редактировать группу");
                System.out.println("4. Редактировать ребенка");
                System.out.println("5. Удалить группу");
                System.out.println("6. Удалить ребенка");
                System.out.println("7. Показать группы с детьми");
                System.out.println("0. Выход");
                System.out.print("Выберите действие: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice == 0) {
                    System.out.println("Выход из программы...");
                    break;
                }

                try {
                    switch (choice) {
                        case 1: 
                            System.out.print("Введите название группы: ");
                            String groupName = scanner.nextLine();
                            System.out.print("Введите номер группы: ");
                            int groupNumber = scanner.nextInt();
                            scanner.nextLine();

                            Group group = new Group(0, groupName, groupNumber);
                            system.addGroup(group);
                            System.out.println("Группа добавлена: " + group.getName());
                            break;

                        case 2: 
                            System.out.print("Введите ФИО ребенка: ");
                            String childName = scanner.nextLine();
                            System.out.print("Введите пол ребенка (М/Ж): ");
                            String gender = scanner.nextLine();
                            System.out.print("Введите возраст ребенка: ");
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите ID группы: ");
                            int groupId = scanner.nextInt();
                            scanner.nextLine();

                            Child child = new Child(0, childName, gender, age, groupId);
                            system.addChild(child);
                            System.out.println("Ребенок добавлен: " + child.getFullName());
                            break;

                        case 3: 
                            System.out.print("Введите ID группы: ");
                            int editGroupId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Введите новое название группы: ");
                            String newGroupName = scanner.nextLine();
                            System.out.print("Введите новый номер группы: ");
                            int newGroupNumber = scanner.nextInt();
                            scanner.nextLine();

                            Group editGroup = new Group(editGroupId, newGroupName, newGroupNumber);
                            system.editGroup(editGroup);
                            System.out.println("Группа обновлена: " + editGroup.getName());
                            break;

                        case 4: 
                            System.out.print("Введите ID ребенка: ");
                            int editChildId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите новое ФИО ребенка: ");
                            String newChildName = scanner.nextLine();
                            System.out.print("Введите новый пол ребенка (М/Ж): ");
                            String newGender = scanner.nextLine();
                            System.out.print("Введите новый возраст ребенка: ");
                            int newAge = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите новый ID группы: ");
                            int newChildGroupId = scanner.nextInt();
                            scanner.nextLine();

                            Child editChild = new Child(editChildId, newChildName, newGender, newAge, newChildGroupId);
                            system.editChild(editChild);
                            System.out.println("Данные ребенка обновлены: " + editChild.getFullName());
                            break;

                        case 5: 
                            System.out.print("Введите ID группы: ");
                            int deleteGroupId = scanner.nextInt();
                            scanner.nextLine();

                            system.deleteGroup(deleteGroupId);
                            System.out.println("Группа удалена.");
                            break;

                        case 6: 
                            System.out.print("Введите ID ребенка: ");
                            int deleteChildId = scanner.nextInt();
                            scanner.nextLine();

                            system.deleteChild(deleteChildId);
                            System.out.println("Ребенок удален.");
                            break;

                        case 7: 
                            system.getGroupsWithChildren();
                            break;

                        default:
                            System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                } catch (SQLException e) {
                    System.out.println("Произошла ошибка: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
