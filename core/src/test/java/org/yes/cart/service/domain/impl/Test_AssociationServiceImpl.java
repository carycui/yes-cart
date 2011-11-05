package org.yes.cart.service.domain.impl;

import org.junit.Before;
import org.junit.Test;
import org.yes.cart.BaseCoreDBTestCase;
import org.yes.cart.constants.ServiceSpringKeys;
import org.yes.cart.domain.entity.Association;
import org.yes.cart.service.domain.AssociationService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class Test_AssociationServiceImpl extends BaseCoreDBTestCase {

    private AssociationService associationService;

    @Before
    public void setUp() throws Exception {
        associationService = (AssociationService) ctx.getBean(ServiceSpringKeys.ASSOCIATION_SERVICE);
    }

    @Test
    public void testFindAll() {
        List<Association> list = associationService.findAll();
        assertEquals(4, list.size());
    }

    @Test
    public void testCreate() {
        Association association = associationService.getGenericDao().getEntityFactory().getByIface(Association.class);
        association.setCode("someCode");
        association.setName("someName");
        association.setDescription("someDescription");
        association = associationService.create(association);
        assertTrue(association.getAssociationId() > 0);
        List<Association> list = associationService.findAll();
        assertEquals(5, list.size());
    }

    @Test
    public void testUpdate() {
        Association association = associationService.getGenericDao().getEntityFactory().getByIface(Association.class);
        association.setCode("someCode");
        association.setName("someName");
        association.setDescription("someDescription");
        association = associationService.create(association);
        assertTrue(association.getAssociationId() > 0);
        association.setCode("someCode2");
        association.setName("someName2");
        association.setDescription("someDescription2");
        association = associationService.update(association);
        assertEquals("someCode2", association.getCode());
        assertEquals("someName2", association.getName());
        assertEquals("someDescription2", association.getDescription());
    }

    @Test
    public void testDelete() {
        Association association = associationService.getGenericDao().getEntityFactory().getByIface(Association.class);
        association.setCode("someCode");
        association.setName("someName");
        association.setDescription("someDescription");
        association = associationService.create(association);
        assertTrue(association.getAssociationId() > 0);
        long pk = association.getAssociationId();
        associationService.delete(association);
        association = associationService.getById(pk);
        assertNull(association);
    }
}