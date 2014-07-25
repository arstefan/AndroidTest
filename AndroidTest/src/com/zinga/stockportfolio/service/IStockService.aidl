package com.zinga.stockportfolio.service;

import com.zinga.stockportfolio.Stock;

interface IStockService {
	Stock addToPortfolio(in Stock stock);
	List<Stock> getPortfolio();
}
