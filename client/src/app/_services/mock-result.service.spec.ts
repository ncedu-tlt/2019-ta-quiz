import { TestBed } from '@angular/core/testing';

import { MockResultService } from './mock-result.service';

describe('MockResultService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MockResultService = TestBed.get(MockResultService);
    expect(service).toBeTruthy();
  });
});
