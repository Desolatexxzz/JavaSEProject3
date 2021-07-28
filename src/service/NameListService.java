package service;

import domain.*;

public class NameListService {
    private static Employee[] employees = null;
    public NameListService(){
        employees = new Employee[Data.EMPLOYEES.length];
        for (int i = 0;i < Data.EMPLOYEES.length;i++){
            int flag = Integer.parseInt(Data.EMPLOYEES[i][0]);
            if (flag == 10){
                int j = 1;
                employees[i] = new Employee(Integer.parseInt(Data.EMPLOYEES[i][j++]),Data.EMPLOYEES[i][j++],Integer.parseInt(Data.EMPLOYEES[i][j++]),Double.parseDouble(Data.EMPLOYEES[i][j++]));
            }else if (flag == 11){
                int flag1 = Integer.parseInt(Data.EQUIPMENTS[i][0]);
                int j = 1;
                Equipment equipment = null;
                if (flag1 == 21){
                     equipment = new PC(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }else if (flag1 == 22){
                     equipment = new NoteBook(Data.EQUIPMENTS[i][j++],Double.parseDouble(Data.EQUIPMENTS[i][j++]));
                }else {
                     equipment = new Printer(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }
                j = 1;
                employees[i] = new Programmer(Integer.parseInt(Data.EMPLOYEES[i][j++]),Data.EMPLOYEES[i][j++],Integer.parseInt(Data.EMPLOYEES[i][j++]),Double.parseDouble(Data.EMPLOYEES[i][j++]),equipment);
            }else if (flag == 12){
                int flag1 = Integer.parseInt(Data.EQUIPMENTS[i][0]);
                int j = 1;
                Equipment equipment = null;
                if (flag1 == 21){
                    equipment = new PC(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }else if (flag1 == 22){
                    equipment = new NoteBook(Data.EQUIPMENTS[i][j++],Double.parseDouble(Data.EQUIPMENTS[i][j++]));
                }else {
                    equipment = new Printer(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }
                j = 1;
                employees[i] = new Designer(Integer.parseInt(Data.EMPLOYEES[i][j++]),Data.EMPLOYEES[i][j++],Integer.parseInt(Data.EMPLOYEES[i][j++]),Double.parseDouble(Data.EMPLOYEES[i][j++]),equipment,Double.parseDouble(Data.EMPLOYEES[i][j++]));
            }else if (flag == 13){
                int flag1 = Integer.parseInt(Data.EQUIPMENTS[i][0]);
                int j = 1;
                Equipment equipment = null;
                if (flag1 == 21){
                    equipment = new PC(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }else if (flag1 == 22){
                    equipment = new NoteBook(Data.EQUIPMENTS[i][j++],Double.parseDouble(Data.EQUIPMENTS[i][j++]));
                }else {
                    equipment = new Printer(Data.EQUIPMENTS[i][j++],Data.EQUIPMENTS[i][j++]);
                }
                j = 1;
                employees[i] = new Architect(Integer.parseInt(Data.EMPLOYEES[i][j++]),Data.EMPLOYEES[i][j++],Integer.parseInt(Data.EMPLOYEES[i][j++]),Double.parseDouble(Data.EMPLOYEES[i][j++]),equipment,Double.parseDouble(Data.EMPLOYEES[i][j++]),Integer.parseInt(Data.EMPLOYEES[i][j++]));
            }
        }
    }
    public Employee[] getAllEmployees(){
            return employees;
    }
    public Employee getEmployee(int id) throws TeamException{
        for (int i = 0; i < employees.length;i++){
            if (employees[i].getId() == id){
                return employees[i];
            }
        }
        throw new TeamException("找不到指定员工");
    }
    public static Boolean chack(int id){
        for (int i = 0; i < employees.length;i++){
            if (employees[i].getId() == id){
                return true;
            }
        }
        return false;
    }

}
