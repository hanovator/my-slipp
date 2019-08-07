package net.slipp.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity {

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_writer"))
	@JsonProperty
	private UserVo writer;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob // VARCHAR(255)보다 훨씬 더 많은 값을 저장 할 수 있다.
	@JsonProperty
	private String contents;
	
	public Answer(){}
	
	public Answer(UserVo writer, String contents, Question question){
		this.writer = writer;
		this.contents = contents;
		this.question = question;
	}
	
	public boolean isSameWriter(UserVo loginUser) {
		return loginUser.equals(this.writer);
	}
	
}
