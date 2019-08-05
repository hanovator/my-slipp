package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private String contents;
	private LocalDateTime createDate;
	
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
}
