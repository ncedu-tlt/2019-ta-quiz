import { TestBed } from '@angular/core/testing';

import { MockStatisticService } from './mock-statistic.service';

describe('MockStatisticService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MockStatisticService = TestBed.get(MockStatisticService);
    expect(service).toBeTruthy();
  });
});
