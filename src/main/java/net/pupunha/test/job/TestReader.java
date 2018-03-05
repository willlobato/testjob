package net.pupunha.test.job;

import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.database.JdbcCursorItemReader;

public class TestReader extends JdbcCursorItemReader {

    @Override
    public void close() throws ItemStreamException {
        super.close();

    }
}
