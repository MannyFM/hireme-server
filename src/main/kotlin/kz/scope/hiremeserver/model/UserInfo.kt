package kz.scope.hiremeserver.model

import kz.scope.hiremeserver.model.audit.DateAudit
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


/**
 * Created by scope team on 01/08/17.
 */

@Entity
@Table(name = "userInfo", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("id"))])
class UserInfo(): DateAudit() {
    constructor(location: String, position: String, company: String, currentRole: String, university: String,
                graduationYear: Int, graduationMonth: String, major: String, degree: String,
                hidden: Boolean, jobType: String, jobField: String, skills: String) : this() {
        this.location = location
        this.position = position
        this.company = company
        this.currentRole = currentRole
        this.university = university
        this.graduationYear = graduationYear
        this.graduationMonth = graduationMonth
        this.major = major
        this.degree = degree
        this.hidden = hidden
        this.jobType = jobType
        this.jobField = jobField
        this.skills = skills
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Size(max = 40)
    lateinit var location: String

    @Size(max = 40)
    lateinit var position: String

    @Size(max = 40)
    lateinit var company: String

    @Size(max = 40)
    @Column(name = "current_role")
    lateinit var currentRole: String

    @Size(max = 40)
    lateinit var university: String

    @Column(name = "graduation_year")
    var graduationYear: Int = 0

    @Size(max = 40)
    @Column(name = "graduation_month")
    lateinit var graduationMonth: String

    @Size(max = 40)
    lateinit var major: String

    @Size(max = 40)
    lateinit var degree: String

    var hidden: Boolean = false

    @Size(max = 40)
    @Column(name = "job_type")
    lateinit var jobType: String

    @Size(max = 40)
    @Column(name = "job_field")
    lateinit var jobField: String

    @Size(max = 40)
    lateinit var skills: String

    @OneToOne(mappedBy = "userInfo")
    lateinit var user: User

}