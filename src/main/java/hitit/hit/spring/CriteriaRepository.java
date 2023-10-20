package hitit.hit.spring;

import hitit.hit.dto.request.PeriodHitRequest;
import hitit.hit.dto.response.PeriodHitResponse;
import hitit.hit.entity.DailyHits;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CriteriaRepository implements DailyHitsRepository {

    private final EntityManager entityManager;

    public CriteriaRepository(final EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Transactional
    public List<DailyHits> findByUrlIdAndPeriod(PeriodHitRequest periodHitRequest) {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DailyHits> criteriaQuery = criteriaBuilder.createQuery(DailyHits.class);
            Root<DailyHits> root = criteriaQuery.from(DailyHits.class);
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("urlId"), periodHitRequest.getUrlId()));
            predicates.add(criteriaBuilder.between(root.get("date"), periodHitRequest.getStartDate(), periodHitRequest.getEndDate()));
            Order asc = criteriaBuilder.asc(root.get("date"));
            criteriaQuery.select(root).where(predicates.toArray(predicates.toArray(new Predicate[0]))).orderBy(asc);
            List<DailyHits> result = entityManager.createQuery(criteriaQuery).getResultList();
            entityManager.close();

        return result;
    }

}
