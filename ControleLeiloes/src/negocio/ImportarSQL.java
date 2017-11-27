/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static java.lang.System.in;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class ImportarSQL {

    public static void execute(String filePath) throws SQLException, FileNotFoundException{
        InputStream in = new FileInputStream(new File(filePath));
        Connection connection = DriverManager.getConnection("jdbc:derby:DB_LEILOES;create=true");
        Statement statement = connection.createStatement();
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|(--\n)");        
        try {
            statement = connection.createStatement();
            while (s.hasNext()) {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/")) {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0) {
                    statement.execute(line);
                }
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
