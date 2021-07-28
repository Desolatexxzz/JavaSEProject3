package view;

import domain.*;
import service.*;

public class TeamView {
        private NameListService listSvc = new NameListService();
        private TeamService teamSvc = new TeamService();
        public void enterMainMenu(){
            System.out.println("-------------------------------------开发团队调度软件--------------------------------------");
            System.out.println("ID\t\t姓名\t\t年龄\t\t工资\t\t职位\t\t状态\t\t奖金\t\t股票\t\t领用设备");
            Employee[] employees = listSvc.getAllEmployees();
            for (int i = 0; i < employees.length;i++){
                if (employees[i].getClass().equals(Employee.class)){
                    System.out.println(employees[i].getId() + "\t\t" + employees[i].getName() + "\t" + employees[i].getAge() + "\t" + employees[i].getSalary());
                }
                if (employees[i].getClass().equals(Programmer.class)){
                    System.out.println(employees[i].getId() + "\t\t" + employees[i].getName() + "\t" + employees[i].getAge() + "\t" + employees[i].getSalary() + "\t" + "程序员" + "\t" + ((Programmer)employees[i]).getStatus() + "\t\t\t" + ((Programmer)employees[i]).getEquipment().getDescription());
                }
                if (employees[i].getClass().equals(Designer.class)){
                    System.out.println(employees[i].getId() + "\t\t" + employees[i].getName() + "\t" + employees[i].getAge() + "\t" + employees[i].getSalary() + "\t" + "设计师" + "\t" + ((Programmer)employees[i]).getStatus() + "\t" + ((Designer)employees[i]).getBonus() + "\t\t" + ((Programmer)employees[i]).getEquipment().getDescription());
                }
                if (employees[i].getClass().equals(Architect.class)){
                    System.out.println(employees[i].getId() + "\t\t" + employees[i].getName() + "\t" + employees[i].getAge() + "\t" + employees[i].getSalary() + "\t" + "设计师" + "\t" + ((Programmer)employees[i]).getStatus() + "\t" + ((Designer)employees[i]).getBonus() + "\t" + ((Architect)employees[i]).getStock() + "\t" + ((Programmer)employees[i]).getEquipment().getDescription());
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
        }
        //显示团队成员列表
        public void getTeam(){
            System.out.println("--------------------团队成员列表---------------------");
            System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
            Programmer[] team = teamSvc.getTeam();
            for (int i = 0; i < teamSvc.getTotal(); i++) {
                if (team[i].getClass().equals(Programmer.class)){
                    System.out.println(team[i].getMemberId() + "/" + team[i].getId() + "      " + team[i].getName() + "      " + team[i].getAge() + "      " + team[i].getSalary() + "      程序员");
                }
                if (team[i].getClass().equals(Designer.class)){
                    System.out.println(team[i].getMemberId() + "/" + team[i].getId() + "      " + team[i].getName() + "      " + team[i].getAge() + "      " + team[i].getSalary() + "      设计师      " + ((Designer)team[i]).getBonus());
                }
                if (team[i].getClass().equals(Architect.class)){
                    System.out.println(team[i].getMemberId() + "/" + team[i].getId() + "      " + team[i].getName() + "      " + team[i].getAge() + "      " + team[i].getSalary() + "      架构师      " + ((Architect)team[i]).getBonus() + "      " + ((Architect)team[i]).getStock());
                }
            }
            System.out.println("-----------------------------------------------------");
        }
        //添加成员操作
        public void addMember(){
            System.out.println("---------------------添加成员---------------------");
            System.out.println("请输入要添加的员工ID：");
            int id = TSUtility.readInt();
            try {
                Employee employee = listSvc.getEmployee(id);
                teamSvc.addMember(employee);
            } catch (TeamException e) {
                System.out.println(e.getMessage());
                TSUtility.readReturn();
                return;
            }
            System.out.println("添加成功");
            TSUtility.readReturn();
        }
        //删除成员操作
    public void deleteMember(){
        System.out.println("---------------------删除成员---------------------");
        System.out.println("请输入要删除员工的TID：");
        int TID = TSUtility.readInt();
        System.out.println("确认是否删除(Y/N)：");
        char flag = TSUtility.readConfirmSelection();
        if (flag == 'Y'){
            try {
                teamSvc.removeMember(TID);
            } catch (TeamException e) {
                System.out.println(e.getMessage());
                TSUtility.readReturn();
                return;
            }
            System.out.println("删除成功");
            TSUtility.readReturn();
        }
    }
    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        boolean flag = true;
        while (flag){
            teamView.enterMainMenu();
            char key = TSUtility.readMenuSelection();
            switch (key){
                case '1':
                    teamView.getTeam();
                    break;
                case '2':
                    teamView.addMember();
                    break;
                case '3':
                    teamView.deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出(Y/N):");
                    char k = TSUtility.readConfirmSelection();
                    if (k == 'Y'){
                        flag = false;
                    }
                    break;
            }
        }
    }
}
