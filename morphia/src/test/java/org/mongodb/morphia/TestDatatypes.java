/**
 * Copyright (C) 2010 Olafur Gauti Gudmundsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.mongodb.morphia;


import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.annotations.Id;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * @author Scott Hernandez
 */
public class TestDatatypes extends TestBase {

  public static class ContainsFloat {
    @Id ObjectId id;
    final float val0 = 1.1F;
    final Float val1 = 1.1F;
  }

  public static class ContainsDouble {
    @Id ObjectId id;
    double val0 = 1.1D;
    Double val1 = 1.1D;
  }

  public static class ContainsShort {
    @Id ObjectId id;
    final short val0 = 1;
    final Short val1 = 1;
  }

  public static class ContainsByte {
    @Id ObjectId id;
    final byte val0 = 1;
    final Byte val1 = 1;
  }

  @Before @Override
  public void setUp() {
    super.setUp();
    morphia.map(ContainsByte.class).map(ContainsDouble.class).map(ContainsFloat.class).map(ContainsShort.class);
  }

  @Test
  public void testByte() throws Exception {
    final ContainsByte cb = new ContainsByte();
    ds.save(cb);
    final ContainsByte loaded = ds.get(cb);

    assertNotNull(loaded);
    assertTrue(loaded.val0 == cb.val0);
    assertTrue(loaded.val1.equals(cb.val1));
  }

  @Test
  public void testShort() throws Exception {
    final ContainsShort cs = new ContainsShort();
    ds.save(cs);
    final ContainsShort loaded = ds.get(cs);

    assertNotNull(loaded);
    assertTrue(loaded.val0 == cs.val0);
    assertTrue(loaded.val1.equals(cs.val1));
  }

  @Test
  public void testFloat() throws Exception {
    final ContainsFloat cf = new ContainsFloat();
    ds.save(cf);
    final ContainsFloat loaded = ds.get(cf);

    assertNotNull(loaded);
    assertTrue(loaded.val0 == cf.val0);
    assertTrue(loaded.val1.equals(cf.val1));
  }
}
