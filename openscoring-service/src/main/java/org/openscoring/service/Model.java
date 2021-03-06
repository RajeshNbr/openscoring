/*
 * Copyright (c) 2015 Villu Ruusmann
 *
 * This file is part of Openscoring
 *
 * Openscoring is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Openscoring is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Openscoring.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openscoring.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jpmml.evaluator.ModelEvaluator;
import org.openscoring.common.Field;

public class Model {

	private ModelEvaluator<?> evaluator = null;

	private Map<String, Object> properties = null;

	private Map<String, List<Field>> schema = null;


	protected Model(){
	}

	public Model(ModelEvaluator<?> evaluator){
		setEvaluator(evaluator);

		Map<String, Object> properties = new LinkedHashMap<>();
		properties.put(Model.PROPERTY_CREATED_TIMESTAMP, new Date());
		properties.put(Model.PROPERTY_ACCESSED_TIMESTAMP, null);

		setProperties(properties);

		setSchema(ModelUtil.encodeSchema(evaluator));
	}

	public ModelEvaluator<?> getEvaluator(){
		return this.evaluator;
	}

	private void setEvaluator(ModelEvaluator<?> evaluator){
		this.evaluator = evaluator;
	}

	public String getSummary(){
		ModelEvaluator<?> evaluator = getEvaluator();

		return evaluator.getSummary();
	}

	public Object putProperty(String key, Object value){
		Map<String, Object> properties = getProperties();

		return properties.put(key, value);
	}

	public Map<String, Object> getProperties(){
		return this.properties;
	}

	private void setProperties(Map<String, Object> properties){
		this.properties = properties;
	}

	public Map<String, List<Field>> getSchema(){
		return this.schema;
	}

	private void setSchema(Map<String, List<Field>> schema){
		this.schema = schema;
	}

	public static final String PROPERTY_CREATED_TIMESTAMP = "created.timestamp";
	public static final String PROPERTY_ACCESSED_TIMESTAMP = "accessed.timestamp";
	public static final String PROPERTY_FILE_SIZE = "file.size";
	public static final String PROPERTY_FILE_MD5SUM = "file.md5sum";
}