import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.sql.ResultSetMetaData;

public class QueryResultTable extends AbstractTableModel {
    private Connection _connection;
    private Statement _statement;
    private ResultSet _resultSet;
    private ResultSetMetaData _resultSetMetaData;
    private int _rowCount;
    public QueryResultTable(Connection connection, Statement statement, ResultSet resultSet, ResultSetMetaData resultSetMetaData) {
        _connection = connection;
        _statement = statement;
        _resultSet = resultSet;
        _resultSetMetaData = resultSetMetaData;
        _rowCount = setRowCount();
        fireTableStructureChanged();
    }

    public int setRowCount() {
        try {
            _resultSet.last();
            return _resultSet.getRow();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getRowCount() {
        return _rowCount;
    }

    @Override
    public int getColumnCount() {
        try {
            return _resultSetMetaData.getColumnCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            _resultSet.next();
            _resultSet.absolute(rowIndex + 1);
            return _resultSet.getObject(columnIndex + 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }

    public Class getColumnClass(int columnIndex) {
        try {
            String className = _resultSetMetaData.getColumnClassName(columnIndex + 1);
            return Class.forName(className);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public String getColumnName(int columnIndex) {
        try {
            return _resultSetMetaData.getColumnName(columnIndex + 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }
}
