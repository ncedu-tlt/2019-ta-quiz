import { TestBed } from '@angular/core/testing';

import { MockUserHistoryService } from './mock-user-history.service';

describe('MockUserHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MockUserHistoryService = TestBed.get(MockUserHistoryService);
    expect(service).toBeTruthy();
  });
});
