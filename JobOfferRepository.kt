package kz.scope.hiremeserver.repository

import kz.scope.hiremeserver.model.JobOffer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by scope team on 02/08/17.
 */
@Repository
interface JobOfferRepository : JpaRepository<JobOffer, Long> {
    fun findByDescriptionOfResponsibilities(descriprion_of_responsibilities: String): List<JobOffer>
    fun findBySkills(skills: String): List<JobOffer>
    fun findByJobType(job_type: String): List<JobOffer>
    fun findByCompanyId(company_id: Long): List<JobOffer>
    fun findByRole(name: String): List<JobOffer>
}
