package com.example.demo.repository.FeedbackRepository;

import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModal, Integer> {
    @Query(value = "select * from gearvn.product_feedback where id_product = :id_product", nativeQuery = true)
    List<FeedbackModal> getFeedbacksByIdProduct(@Param("id_product") int id_product);

    @Query(value = "select * from gearvn.product_feedback where id_feedback = :id_feedback", nativeQuery = true)
    FeedbackModal findFeedbackProductById(@Param("id_feedback") int id_feedback);
}
