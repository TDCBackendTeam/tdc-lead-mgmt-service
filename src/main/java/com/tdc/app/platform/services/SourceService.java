package com.tdc.app.platform.services;

import java.util.Map;

import com.tdc.app.platform.entities.Source;

public interface SourceService {

	Map<String, Object> getSources();

	Map<String, Object> getSourceById(int sourceId);

	Map<String, Object> createSource(Source source);

	Map<String, Object> updateSourceById(int sourceId, Source source);

	Map<String, Object> deleteSourceById(int sourceId);
}
