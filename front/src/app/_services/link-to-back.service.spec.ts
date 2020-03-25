import { TestBed } from '@angular/core/testing';

import { LinkToBackService } from './link-to-back.service';

describe('LinkToBackService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LinkToBackService = TestBed.get(LinkToBackService);
    expect(service).toBeTruthy();
  });
});
