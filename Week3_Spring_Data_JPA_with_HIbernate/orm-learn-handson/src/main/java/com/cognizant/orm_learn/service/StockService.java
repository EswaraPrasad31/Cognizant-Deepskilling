package com.cognizant.orm_learn.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<Stock> getFacebookStocksSeptember2019() {
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end = LocalDate.of(2019, 9, 30);
        return stockRepository.findByCodeAndDateBetween("FB", start, end);
    }

    @Transactional(readOnly = true)
    public List<Stock> getGoogleStocksGreaterThan1250() {
        return stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
    }

    @Transactional(readOnly = true)
    public List<Stock> getTop3HighestVolumeStocks() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    @Transactional(readOnly = true)
    public List<Stock> getTop3LowestNetflixStocks() {
        return stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
    }
}
