package com.fmr.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fmr.portfolio.model.PortfolioDto;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioDto,String> {
	
	@Query(value="Select * from portfolio p where p.custid = ?1",nativeQuery = true)
    List<PortfolioDto> retrievePortfolio(String custid);
}
