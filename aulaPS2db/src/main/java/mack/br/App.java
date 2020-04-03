package mack.br;
import java.sql.*;
import java.util.Scanner;
//Nathan Novais Borges
//TIA: 31987869
public class App{
        public static void main( String[] args ) {
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

                String db = "aula_ps2";
                String url = "jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&useJDBCCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user = "root";
                String psw = "";
                conn = DriverManager.getConnection(url, user, psw);
                
                
                menu();
                int x = escolhe();
                while(x !=0){
                if(x==0){
                    System.out.println("Conexão Encerrada");
                    
                    menu();
                    x = escolhe();
                    
                }
                else if(x==1){
                    //MÉTODO PARA INSERIR
                    String sql = "INSERT INTO frequencia(tia,entrada,saida) VALUES(?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    
                    
                    Scanner input = new Scanner(System.in);  
                    System.out.println("Digite o TIA");
                    String tia = input.nextLine();
                    System.out.println("Digite a entrada");
                    String entrada = input.nextLine();
                    System.out.println("Digite a saída");
                    String saida = input.nextLine();
                    
                    stmt.setString(1, tia);
                    stmt.setString(2, entrada);
                    stmt.setString(3, saida);
                    stmt.execute();
                    stmt.close();
                    menu();
                    x = escolhe();
                    
                }
                
                else if(x==2){
                    //MÉTODO PARA CONSULTAR
                    String sql = "Select * FROM frequencia";
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();

                    while (rs.next()) {
                        String tia = rs.getString("tia");
                        String entrada = rs.getString("entrada");
                        String saida = rs.getString("saida");
                        System.out.println("TIA: " + tia + "; Entrada: " + entrada + "; Saída:" + saida + "");
                        
                    }rs.close();
                    menu();
                    x = escolhe();
                }
                }
                
                
                conn.close();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("SQL EXCEPTION");
                e.printStackTrace();
            }
        }
    public static void menu(){
            System.out.println("Tabela de Frequência");
            System.out.println("Para inserir na tabela 1");
            System.out.println("Para consultar a tabela digite 2");
            System.out.println("Para encerrar conexão digite 0");


    }
    public static int escolhe(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione a opção");
        int x = input.nextInt();
        return x;
    }
    

}