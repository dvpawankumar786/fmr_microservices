package com.fmr.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fmr.stock.model.MasterStock;

@Repository
public interface StockMasterRepo extends JpaRepository<MasterStock,String> {

}
