import { TestBed } from '@angular/core/testing';

import { QStorageManipulateService } from './q-storage-manipulate.service';

describe('QStorageManipulateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QStorageManipulateService = TestBed.get(QStorageManipulateService);
    expect(service).toBeTruthy();
  });
});
