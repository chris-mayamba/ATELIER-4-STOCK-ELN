package org.example.Json;

import org.example.Data.Stock;

public interface StockRepository {
    void save(Stock stock);
    Stock load();
}

