import { TestBed } from '@angular/core/testing';

import { AuthVarService } from './auth-var.service';

describe('AuthVarService', () => {
  let service: AuthVarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthVarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
