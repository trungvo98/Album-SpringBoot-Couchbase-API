package com.lugd.album.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;


@Configuration
@EnableCouchbaseRepositories
public class CouchBaseConfig extends AbstractCouchbaseConfiguration {
	

	@Value("${couchbase.bootstrap-hosts}")
	private String couchbaseHost;
	
	@Value("${couchbase.name}")
	private String couchbaseName;
	
	@Value("${couchbase.pass}")
	private String couchbasePass;
	
	@Value("${couchbase.bucket.name}")
	private String couchbaseBucketName;
	
	@Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList(this.couchbaseHost);
    }

    @Override
    protected String getBucketName() {
        return this.couchbaseBucketName;
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }
    
    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, false, false);    //auto indexing for views only
    }
    
    @Override
    protected CouchbaseEnvironment getEnvironment() {
    	return DefaultCouchbaseEnvironment.builder().connectTimeout(30000).socketConnectTimeout(30000).build();
    }
    
    @Override
    public String typeKey() {
        return MappingCouchbaseConverter.TYPEKEY_DEFAULT;
    }

    @Override
    @Bean(name = BeanNames.COUCHBASE_CLUSTER_INFO)
    public ClusterInfo couchbaseClusterInfo() throws Exception {
        return couchbaseCluster().authenticate(this.couchbaseName,this.couchbasePass).clusterManager().info();
    }
    
    @Override
	@Bean(destroyMethod = "close", name = BeanNames.COUCHBASE_BUCKET)
	public com.couchbase.client.java.Bucket couchbaseClient() throws Exception {
		// @Bean method can use another @Bean method in the same @Configuration
		// by directly invoking it
		return couchbaseCluster().openBucket(getBucketName());
	}

}
