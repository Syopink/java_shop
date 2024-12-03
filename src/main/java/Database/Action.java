/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Action {
        private final Connect cn = new Connect();
    private Connection conn;

    public Action() {
        this.conn = cn.connectSQL(); // Mở kết nối trong hàm tạo
    }
}
