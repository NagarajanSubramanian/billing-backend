package crackers.traders.janani.table.component;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="master_def")
@AllArgsConstructor
@NoArgsConstructor
public class MasterDef {

	@Id
	@Column(name="master_id")
	public String masterId;

	@Column(name="table_name")
	public String tableName;
	
	@Column(name="key_field_id")
	public String keyFieldId;
	
	@Column(name="name_field_id")
	public String nameFieldId;
	
	@Column(name="show_field_ids")
	public String showFieldIds;
	
	@Column(name="filter_field_ids")
	public String filterFieldIds;
	
	@Column(name="created_user")
	public String createdUser;
	
	@Column(name="created_date")
	public ZonedDateTime createdDate;
	
	@Column(name="updated_user")
	public String updatedUser;
	
	@Column(name="updated_date")
	public ZonedDateTime updatedDate;
}
