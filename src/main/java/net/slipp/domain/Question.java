package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question{
	@Id
	@GeneratedValue
	private Long id;
	
	// user에서 question과 관계를 맺을수도 있지만, 가장 상위에 있으므로 너무 많은 관계를 갖을 수 있다. 그러므로 question을 user에 관계맺는다.
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
	private UserVo writer;

	private String title;
	
	@Lob // VARCHAR(255)보다 훨씬 더 많은 값을 저장 할 수 있다.
	private String contents;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
	public Question(){}
	
	public Question(UserVo writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	
	public String getFormattedCreateDate() {
		if(createDate == null){
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(UserVo loginUser) {
		return this.writer.equals(loginUser);
	}
	
}
