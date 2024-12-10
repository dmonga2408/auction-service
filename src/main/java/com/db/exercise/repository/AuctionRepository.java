package com.db.exercise.repository;

import com.db.exercise.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,String> {

    @Query(value = "SELECT a FROM Auction a where a.endTime IS NULL")
    public List<Auction> findAllActiveAuctions();

}
